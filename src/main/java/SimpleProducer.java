import com.google.gson.Gson;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.StringSchema;
import org.apache.pulsar.shade.org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.Date;

public class SimpleProducer {

    private static final String SERVICE_URL = "pulsar+ssl://pulsar-gcp-useast1.streaming.datastax.com:6651";

    /*public static void main(String[] args) throws IOException {
        // Create client object
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .authentication(
                        AuthenticationFactory.token("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODk2NjI5ODMsImlzcyI6ImRhdGFzdGF4Iiwic3ViIjoiY2xpZW50Ozk2Yzg1ZmE5LTU4ZjEtNGE5ZC05OTFhLWFjN2RiM2ZlNzYwODtZVzUwYjI1MFpYTjBkR1Z1WVc1MDsyMzVhM2VlNzFmIiwidG9rZW5pZCI6IjIzNWEzZWU3MWYifQ.PJC5BXSSKONGALUtchj6GG2BgW6p70nFrgyuVr7EwolVTZxgkKtNXDPsPNouOa16EpU7KiMokeR0-Hs9wq6wiGwHREOM1VrEviNwJ37JNSngcTQLquU8dUiVbPDaKCPegWRjHnfkD7JWmV5JqRe2eWNMscW8FwcAKcO7VguO0ViVsHgsdeYgDqgbTSvC-uYx3qj4F9UqkRKqJd_XtiQ_VxOgRHYkTupCCHD1wj-cAMniXlqRd7A6LgR2PFlUmY3VKMrycBrFjKukuzgZ_aA8kKGs-Exr9QxxQ-p0AF_SP3ag9Bv9ssNfNHD7xUN1L4ADKvX-dhhpo1bV2YOvJ4zXFw")
                )
                .build();

        // Create producer on a topic
        Producer<User> producer = client.newProducer(Schema.JSON(User.class))
                .topic("persistent://antontesttenant/default/testtopic")
                .create();

        for (int i = 0; i < 100; i++) {

            User u = new User();
            u.setAge(i);
            u.setName("anton " + i);
            // Send a message to the topic
            producer.send(u);
        }





        *//*
        Producer<byte[]> producer = client.newProducer(Schema.AUTO_PRODUCE_BYTES())
                .topic("persistent://antontesttenant/default/testtopic")
                .create();

        for (int i = 0; i < 100; i++) {

            User u = new User();
//            u.setTest(i + "");
//            u.setAge(i);
            u.setName("anton " + i);
            // Send a message to the topic
            producer.send(SerializationUtils.serialize(u));
        * *//*





        //Close the producer
        producer.close();

        // Close the client
        client.close();
    }*/


    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        // Send with AVRO schema
//        Producer<User> producer = client.newProducer(Schema.AVRO(User.class))
//                .topic("persistent://public/default/mytopic")
//                .create();
//        for (int i = 0; i < 1; i++) {
//            User u = new User();
//            producer.send(u);
//        }

        // Send with JSON schema
//        Producer<User> producer = client.newProducer(Schema.JSON(User.class))
//                .topic("persistent://public/default/mytopic")
//                .create();
//
//        for (int i = 0; i < 1; i++) {
//            User u = new User();
//            producer.send(u);
//        }


        // Send with serialization
        // this will be considered as normal string in sink and ignored
//        Producer<byte[]> producer = client.newProducer(Schema.AUTO_PRODUCE_BYTES())
//                .topic("persistent://public/default/mytopic")
//                .create();
//
//        for (int i = 0; i < 1; i++) {
//
//            User u = new User();
//            producer.send(SerializationUtils.serialize(u));
//        }


        // Send with serialization
        // this will be considered as normal string in sink and ignored
//        Producer<byte[]> producer = client.newProducer()
//                .topic("persistent://public/default/mytopic")
//                .create();
//        for (int i = 0; i < 1; i++) {
//            User u = new User();
//            u.setName("user name");
//            u.setAge(23);
//            u.setAddress("address");
//            u.setJoiningDate(new Date());
//            Employer employer = new Employer();
//            employer.setEmployerName("test name");
//            employer.setEmployerNumber(123L);
//            u.setEmployer(employer);
//            producer.send(SerializationUtils.serialize(u));
//        }

         // Send as normal string but valid JSON
        Producer<byte[]> producer = client.newProducer()
                .topic("persistent://public/default/mytopic")
                .create();
        for (int i = 0; i < 1; i++) {
            User u = new User();
            u.setName("user name");
            u.setAge(23);
            u.setAddress("address");
            u.setJoiningDate(new Date());
            Employer employer = new Employer();
            employer.setEmployerName("test name");
            employer.setEmployerNumber(123L);
            u.setEmployer(employer);
            String s = gson.toJson(u);
            producer.send(s.getBytes());
        }

        // Send a plain text
//        Producer<byte[]> producer = client.newProducer()
//                .topic("persistent://public/default/mytopic")
//                .create();
//        for (int i = 0; i < 1; i++) {
//            producer.send("A string message".getBytes());
//        }





        producer.close();
        client.close();
    }

}
