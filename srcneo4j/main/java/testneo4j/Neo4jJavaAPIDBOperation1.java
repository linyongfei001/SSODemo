package testneo4j;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import java.io.File;

/*
直接操作数据库文件（确保服务已关闭）：
 */

public class Neo4jJavaAPIDBOperation1 {
    public static void main(String[] args) {
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService db = dbFactory.newEmbeddedDatabase(new File("D:/soft/neo4j-community-3.4.1/data/databases/graph.db"));
        try (Transaction tx = db.beginTx()) {

            Node javaNode = db.createNode(Tutorials.JAVA);
            javaNode.setProperty("TutorialID", "JAVA001");
            javaNode.setProperty("Title", "Learn Java");
            javaNode.setProperty("NoOfChapters", "25");
            javaNode.setProperty("Status", "Completed");

            Node scalaNode = db.createNode(Tutorials.SCALA);
            scalaNode.setProperty("TutorialID", "SCALA001");
            scalaNode.setProperty("Title", "Learn Scala");
            scalaNode.setProperty("NoOfChapters", "20");
            scalaNode.setProperty("Status", "Completed");

            Relationship relationship = javaNode.createRelationshipTo(
                    scalaNode, TutorialRelationships.JVM_LANGIAGES);
            relationship.setProperty("Id", "1234");
            relationship.setProperty("OOPS", "YES");
            relationship.setProperty("FP", "YES");

            tx.success();
        }
        System.out.println("Done successfully");
    }
}

enum Tutorials implements Label {
    JAVA,SCALA,SQL,NEO4J;
}

enum TutorialRelationships implements RelationshipType {
    JVM_LANGIAGES, NON_JVM_LANGIAGES;
}