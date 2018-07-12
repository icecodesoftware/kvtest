package com.github.icecodesoftware;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.github.icecodesoftware.generated.Key;
import com.github.icecodesoftware.generated.LookupServiceGrpc.LookupServiceImplBase;
import com.github.icecodesoftware.generated.Value;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class LookupServer {
  private static final Logger logger = Logger.getLogger(LookupServer.class.getName());

  private Server server;
  static Map<String, String> map = new HashMap<>();
  Random random = new Random();

  private void start() throws IOException {
    IntStream.range(0, 10000).forEach(i -> map.put("" + random.nextInt(100000), "purge"));

    /* The port on which the server should run */
    int port = 50051;
    server = ServerBuilder.forPort(port).addService(new LookupServiceImpl()).build().start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        LookupServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    final LookupServer server = new LookupServer();
    server.start();
    server.blockUntilShutdown();
  }

  static class LookupServiceImpl extends LookupServiceImplBase {

    // Map<String,String> map = Map.of("bob","good","alice","bad");
    @Override
    public StreamObserver<Key> get(StreamObserver<Value> responseObserver) {
      return new StreamObserver<Key>() {

        @Override
        public void onNext(Key key) {
          String value = map.getOrDefault(key, "");
          Value reply = Value.newBuilder().setValue(value).build();
          responseObserver.onNext(reply);
        }

        @Override
        public void onError(Throwable t) {
          logger.log(Level.WARNING, "Error occurred", t);
        }

        @Override
        public void onCompleted() {
          responseObserver.onCompleted();
        }
      };
    }
  }
}
