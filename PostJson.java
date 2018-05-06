
public class PostJson {
private void postJsonData() {
        List<Constraint> constraints = readConstraintsFromJSONFile("/Users/sojjwal/Documents/constraints.txt");
        System.out.println("POST JSON DATA - START");

        Client httpClient = newHttpClient();

        URI uri = UriBuilder.fromUri("URL").build();

        for(Constraint constraint : constraints) {
            Response response = httpClient.target(uri).request().post(Entity.entity(constraint, MediaType.APPLICATION_JSON));
            System.out.println("Response for " + constraint.getName() + " is: " + response.getStatus());
        }
        System.out.println("POST JSON DATA - END");
    }
    private static List<Constraint> readConstraintsFromJSONFile(String file) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Constraint> constraints = new LinkedList<>();

        try {
            List<Constraint> foundConstraints = objectMapper.readValue(new File(file), new TypeReference<List<Constraint>>() {});
            constraints.addAll(foundConstraints);

            System.out.println(objectMapper.writeValueAsString(constraints) );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return constraints;
    }
    
    }
