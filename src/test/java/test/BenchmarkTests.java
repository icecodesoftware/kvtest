package test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeysPublic;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.apache.hadoop.io.BloomMapFile;
import org.apache.hadoop.io.BloomMapFile.Reader;
import org.apache.hadoop.io.BloomMapFile.Writer;
import org.apache.hadoop.io.Text;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.lmdbjava.Dbi;
import org.lmdbjava.DbiFlags;
import org.lmdbjava.Env;
import org.lmdbjava.Txn;

import com.google.common.base.Stopwatch;



public class BenchmarkTests {

  @Rule
  public final TemporaryFolder tmp = new TemporaryFolder();
  Random random = new Random();
  List<String> states = Arrays.asList("purge", "discover");
  long mapSize = 10_000_000L;
  long mapSizeBytes = UUID.randomUUID().toString().length() * mapSize * states.get(1).length();
  int lookups = 100_000_000;

  @Test
  public void testLMDB() throws IOException {
    System.out.printf("mapSize: %s maxBytes:%s\n", mapSize, mapSizeBytes);
    final File path = tmp.newFolder();
    final Env<ByteBuffer> env = Env.create().setMapSize(mapSizeBytes).setMaxDbs(1).open(path);
    Dbi<ByteBuffer> db = env.openDbi("db", DbiFlags.MDB_CREATE);
    final ByteBuffer key = ByteBuffer.allocateDirect(env.getMaxKeySize());
    final ByteBuffer val = ByteBuffer.allocateDirect(700);
    int count = 0;
    Stopwatch sw = Stopwatch.createStarted();
    try (Txn<ByteBuffer> txn = env.txnWrite()) {
      for (int i = 0; i < mapSize; i++) {
        String keyStr = "" + random.nextInt(100_000_000);
        String valueStr = states.get(random.nextInt(states.size()));
        key.put(keyStr.getBytes(StandardCharsets.UTF_8)).flip();
        val.put(valueStr.getBytes(StandardCharsets.UTF_8)).flip();
        count += 1;
        if (count % 500000 == 0) {
          System.out.println("at entry:" + count);
        }
        db.put(txn, key, val);
        key.clear();
        val.clear();
      }
      txn.commit();
    }
    System.out.println("put took " + sw.stop());
    System.out.println("lmdb size: " + FileUtils.sizeOfDirectory(path));

    sw = Stopwatch.createStarted();
    int matches = 0;
    int readCount = 0;
    try (Txn<ByteBuffer> txn = env.txnRead()) {
      for (int i = 0; i < lookups; i++) {
        String ikey = "" + random.nextInt(1_000_000);
        key.clear();
        key.put(ikey.getBytes(StandardCharsets.UTF_8)).flip();
        ByteBuffer found = db.get(txn, key);
        readCount += 1;
        if (readCount % 1000000 == 0) {
          System.out.println("at entry:" + readCount);
        }
        if (found != null) {
          matches += 1;
        }
        txn.val();
      }
    }

    System.out.printf("matches %s read took %s\n", matches, sw.stop());
  }

  @Test
  public void testHashMapLookup() {
    var map = createMap();
    Stopwatch sw = Stopwatch.createStarted();
    for (int i = 0; i < lookups; i++) {
      String key = "" + random.nextInt(1_000_000);
      map.get(key);
    }
    System.out.println("hashmap lookups took " + sw.stop());
  }

  Map<String, String> createMap() {
    HashMap<String, String> map = new HashMap<>();
    var sw = Stopwatch.createStarted();
    for (int i = 0; i < mapSize; i++) {
      String keyStr = "" + random.nextInt(100_000_000);
      String valueStr = states.get(random.nextInt(states.size()));
      map.put(keyStr, valueStr);
    }
    System.out.println("hashmap put took " + sw.stop());
    return map;
  }

  NavigableMap<String, String> createSortedMap() {
    TreeMap<String, String> map = new TreeMap<>();
    var sw = Stopwatch.createStarted();
    for (int i = 0; i < mapSize; i++) {
      String keyStr = "" + random.nextInt(100_000_000);
      String valueStr = states.get(random.nextInt(states.size()));
      map.put(keyStr, valueStr);
    }
    System.out.println("hashmap put took " + sw.stop());
    return map;
  }


  @Test
  public void testBloomMap() throws IOException {
    final File dir = tmp.newFolder();
    Configuration conf = new Configuration();
    conf.set("fs.file.impl", RawLocalFileSystem.class.getName());
    System.out.println(dir.getAbsolutePath());
    Path path = new Path(dir.getAbsolutePath());
    var sw = Stopwatch.createStarted();
    Writer.setIndexInterval(conf, 128);
    conf.setLong(CommonConfigurationKeysPublic.IO_MAPFILE_BLOOM_SIZE_KEY, mapSize);
    try (BloomMapFile.Writer writer =
        new Writer(conf, path, Writer.keyClass(Text.class), Writer.valueClass(Text.class))) {
      var map = createSortedMap();
      for (var entry : map.entrySet()) {
        writer.append(new Text(entry.getKey()), new Text(entry.getValue()));
      }
      map.clear();
    }
    System.out.println("bloommap append took " + sw.stop());
    System.out.println("lmdb size: " + FileUtils.sizeOfDirectory(dir));
    sw = Stopwatch.createStarted();
    Text val = new Text();
    Text keyT = new Text();
    int readCount = 0;
    try (BloomMapFile.Reader reader = new Reader(path, conf)) {
      for (int i = 0; i < lookups; i++) {
        String key = "" + random.nextInt(1_000_000);
        keyT.set(key);
        val.clear();
        if (readCount % 1000000 == 0) {
          System.out.println("at entry:" + readCount);
        }
        reader.get(new Text(key), val);
        readCount += 1;
      }
    }
    System.out.println("bloommap lookups took " + sw.stop());

  }
}
