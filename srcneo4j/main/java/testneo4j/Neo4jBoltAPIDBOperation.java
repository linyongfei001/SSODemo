package testneo4j;

import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4jBoltAPIDBOperation {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Driver driver = GraphDatabase.driver("bolt://127.0.0.1:7687",
                AuthTokens.basic("neo4j", "123456"));
        Session session = driver.session();

//        session.run("CREATE (a:Person {name: {name}, title: {title}})",
//                parameters("name", "gm", "title", "King"));

     /*   StatementResult result = session.run(
                "MATCH (a:Person) WHERE a.name = {name} "
                        + "RETURN a.name AS name, a.title AS title",
                parameters("name", "gm"));

        while (result.hasNext()) {
            Record record = (Record) result.next();
            System.out.println(record.get("title").asString() + " "
                    + record.get("name").asString());
        }
        */
        StatementResult result = session.run(
                "MATCH (n:明星) where n.名称 = {name} RETURN n ",
                parameters("name","王菲"));


        while (result.hasNext()) {
            Record record = (Record) result.next();
            System.out.println(record.get("名称").asString() + " "
                    + record.get("明星").asString());
        }

        session.close();
        driver.close();
    }
}
