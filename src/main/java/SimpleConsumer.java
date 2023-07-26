import org.apache.pulsar.client.api.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SimpleConsumer {

    private static final String SERVICE_URL = "pulsar+ssl://pulsar-gcp-useast1.streaming.datastax.com:6651";

    public static void main(String[] args) throws IOException {
        // Create client object
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .authentication(
                        AuthenticationFactory.token("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODk2NjI5ODMsImlzcyI6ImRhdGFzdGF4Iiwic3ViIjoiY2xpZW50Ozk2Yzg1ZmE5LTU4ZjEtNGE5ZC05OTFhLWFjN2RiM2ZlNzYwODtZVzUwYjI1MFpYTjBkR1Z1WVc1MDsyMzVhM2VlNzFmIiwidG9rZW5pZCI6IjIzNWEzZWU3MWYifQ.PJC5BXSSKONGALUtchj6GG2BgW6p70nFrgyuVr7EwolVTZxgkKtNXDPsPNouOa16EpU7KiMokeR0-Hs9wq6wiGwHREOM1VrEviNwJ37JNSngcTQLquU8dUiVbPDaKCPegWRjHnfkD7JWmV5JqRe2eWNMscW8FwcAKcO7VguO0ViVsHgsdeYgDqgbTSvC-uYx3qj4F9UqkRKqJd_XtiQ_VxOgRHYkTupCCHD1wj-cAMniXlqRd7A6LgR2PFlUmY3VKMrycBrFjKukuzgZ_aA8kKGs-Exr9QxxQ-p0AF_SP3ag9Bv9ssNfNHD7xUN1L4ADKvX-dhhpo1bV2YOvJ4zXFw")
                )
                .build();

        // Create consumer on a topic with a subscription
        Consumer consumer = client.newConsumer()
                .topic("antontesttenant/default/testtopic")
                .subscriptionName("INSERT_SUBSCRIPTION_HERE")
                .subscribe();

        boolean receivedMsg = false;
        // Loop until a message is received
        try {
            do {
                // Block for up to 1 second for a message
                Message msg = consumer.receive(1, TimeUnit.SECONDS);

                if(msg != null){
                    System.out.printf("Message received: %s", new String(msg.getData()));
                    System.out.println("");

                    // Acknowledge the message to remove it from the message backlog
                    consumer.acknowledge(msg);

                    receivedMsg = true;
                }

            } while (true);
        } finally {
            //Close the consumer
            consumer.close();

            // Close the client
            client.close();
        }



    }
}
