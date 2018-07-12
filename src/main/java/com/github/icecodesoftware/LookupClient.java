package com.github.icecodesoftware;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.icecodesoftware.generated.Key;
import com.github.icecodesoftware.generated.LookupServiceGrpc;
import com.github.icecodesoftware.generated.Value;
import com.google.common.base.Stopwatch;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class LookupClient {
  private static final Logger logger = Logger.getLogger(LookupClient.class.getName());

  private final ManagedChannel channel;
  private final LookupServiceGrpc.LookupServiceStub asyncStub;

  /** Construct client connecting to HelloWorld server at {@code host:port}. */
  public LookupClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext().build());
  }

  /** Construct client for accessing HelloWorld server using the existing channel. */
  LookupClient(ManagedChannel channel) {
    this.channel = channel;
    asyncStub = LookupServiceGrpc.newStub(channel);

  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /** Say hello to server. */
  public StreamObserver<Key> get(StreamObserver<Value> responseObsever) {
    try {
      return asyncStub.get(responseObsever);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      throw e;
    }
  }

  /**
   * Greet server. If provided, the first element of {@code args} is the name to use in the
   * greeting.
   */
  public static void main(String[] args) throws Exception {
    LookupClient client = new LookupClient("localhost", 50051);
    Stopwatch sw = Stopwatch.createStarted();

    try {
      Stopwatch sw2 = Stopwatch.createStarted();
      CountDownLatch finishLatch = new CountDownLatch(1);
      StreamObserver<Key> requestObserver = client.get(new ResponseObserver(finishLatch, sw));
      /* Access a service running on the local machine on port 50051 */
      System.out.println("time " + sw2.stop());
      requestObserver.onCompleted();
      finishLatch.await();
    } finally {
      client.shutdown();
    }
  }
}


class ResponseObserver implements StreamObserver<Value> {
  private static final Logger logger = Logger.getLogger(ResponseObserver.class.getName());
  CountDownLatch finishLatch = new CountDownLatch(1);
  private Stopwatch sw;
  int count = 0;

  public ResponseObserver(CountDownLatch finishLatch, Stopwatch sw) {
    this.finishLatch = finishLatch;
    this.sw = sw;
  }

  @Override
  public void onNext(Value value) {
    count += 1;
    if (count % 10000 == 0) {
      System.out.printf("time: %s\n", sw);
    }

  }

  @Override
  public void onError(Throwable t) {
    logger.log(Level.WARNING, "Failed", t);
    finishLatch.countDown();
  }

  @Override
  public void onCompleted() {
    finishLatch.countDown();
    System.out.printf("count %s time %s", count, sw);
  }

}
