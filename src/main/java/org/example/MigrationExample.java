package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class MigrationExample {
    public void migrate() {
        String connectionString = "mongodb+srv://Cluster78301:WUV7WEZman1J@cluster78301.zfynw98.mongodb.net/?retryWrites=true&w=majority&appName=Cluster78301";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("CDRecords");

            // Adiciona músicos
            MongoCollection<Document> musicoCollection = database.getCollection("Musico");
            Document kurtCobain = new Document("nome", "Kurt Cobain")
                    .append("descricao", "Vocalista e Guitarrista")
                    .append("genero", "Rock")
                    .append("cep", "12345678")
                    .append("rua", "Aberdeen St")
                    .append("cidade", "Aberdeen")
                    .append("estado", "WA")
                    .append("telefone", "111-111-1111");
            musicoCollection.insertOne(kurtCobain);

            Document kristNovoselic = new Document("nome", "Krist Novoselic")
                    .append("descricao", "Baixista")
                    .append("genero", "Rock")
                    .append("cep", "12345678")
                    .append("rua", "Aberdeen St")
                    .append("cidade", "Aberdeen")
                    .append("estado", "WA")
                    .append("telefone", "222-222-2222");
            musicoCollection.insertOne(kristNovoselic);

            Document daveGrohl = new Document("nome", "Dave Grohl")
                    .append("descricao", "Baterista")
                    .append("genero", "Rock")
                    .append("cep", "12345678")
                    .append("rua", "Aberdeen St")
                    .append("cidade", "Aberdeen")
                    .append("estado", "WA")
                    .append("telefone", "333-333-3333");
            musicoCollection.insertOne(daveGrohl);

            // Adiciona banda
            MongoCollection<Document> bandaCollection = database.getCollection("Banda");
            Document nirvana = new Document("nome", "Nirvana")
                    .append("descricao", "Banda de Grunge")
                    .append("genero", "Grunge")
                    .append("dataDeFormacao", "1987-01-01");
            bandaCollection.insertOne(nirvana);

            // Integra músicos à banda
            MongoCollection<Document> integrarCollection = database.getCollection("Integrar");
            List<Document> integrantes = Arrays.asList(
                    new Document("musicoId", kurtCobain.getObjectId("_id")).append("bandaId", nirvana.getObjectId("_id")),
                    new Document("musicoId", kristNovoselic.getObjectId("_id")).append("bandaId", nirvana.getObjectId("_id")),
                    new Document("musicoId", daveGrohl.getObjectId("_id")).append("bandaId", nirvana.getObjectId("_id"))
            );
            integrarCollection.insertMany(integrantes);

            // Adiciona disco Nevermind
            MongoCollection<Document> discoCollection = database.getCollection("Disco");
            Document nevermind = new Document("dataLancamento", "1991-09-24")
                    .append("preco", 19.99)
                    .append("platinas", 10)
                    .append("titulo", "Nevermind")
                    .append("formato", "CD")
                    .append("descricao", "Álbum icônico de grunge")
                    .append("genero", "Grunge");
            discoCollection.insertOne(nevermind);

            // Adiciona músicas ao disco Nevermind
            MongoCollection<Document> musicaCollection = database.getCollection("Musica");
            List<Document> musicas = Arrays.asList(
                    new Document("duracao", 5.01).append("faixa", 1).append("autores", "Kurt Cobain").append("titulo", "Smells Like Teen Spirit").append("letra", "Load up on guns..."),
                    new Document("duracao", 4.14).append("faixa", 2).append("autores", "Kurt Cobain").append("titulo", "In Bloom").append("letra", "Sell the kids for food..."),
                    new Document("duracao", 3.38).append("faixa", 3).append("autores", "Kurt Cobain").append("titulo", "Come As You Are").append("letra", "Come as you are..."),
                    new Document("duracao", 3.03).append("faixa", 4).append("autores", "Kurt Cobain").append("titulo", "Breed").append("letra", "I don't care..."),
                    new Document("duracao", 4.17).append("faixa", 5).append("autores", "Kurt Cobain").append("titulo", "Lithium").append("letra", "I'm so happy..."),
                    new Document("duracao", 2.57).append("faixa", 6).append("autores", "Kurt Cobain").append("titulo", "Polly").append("letra", "Polly wants a cracker..."),
                    new Document("duracao", 2.23).append("faixa", 7).append("autores", "Kurt Cobain").append("titulo", "Territorial Pissings").append("letra", "When I was an alien..."),
                    new Document("duracao", 3.44).append("faixa", 8).append("autores", "Kurt Cobain").append("titulo", "Drain You").append("letra", "One baby to another..."),
                    new Document("duracao", 3.52).append("faixa", 9).append("autores", "Kurt Cobain").append("titulo", "Lounge Act").append("letra", "Truth covered in security..."),
                    new Document("duracao", 3.32).append("faixa", 10).append("autores", "Kurt Cobain").append("titulo", "Stay Away").append("letra", "Monkey see, monkey do..."),
                    new Document("duracao", 3.16).append("faixa", 11).append("autores", "Kurt Cobain").append("titulo", "On a Plain").append("letra", "I'll start this off..."),
                    new Document("duracao", 3.52).append("faixa", 12).append("autores", "Kurt Cobain").append("titulo", "Something in the Way").append("letra", "Underneath the bridge..."),
                    new Document("duracao", 6.43).append("faixa", 13).append("autores", "Kurt Cobain").append("titulo", "Endless, Nameless").append("letra", "Silence, here I am...")
            );
            musicaCollection.insertMany(musicas);

            // Relaciona músicas ao disco Nevermind
            MongoCollection<Document> incluirCollection = database.getCollection("Incluir");
            List<Document> incluirMusicas = Arrays.asList(
                    new Document("musicaId", musicas.get(0).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(1).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(2).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(3).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(4).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(5).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(6).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(7).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(8).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(9).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(10).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(11).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id")),
                    new Document("musicaId", musicas.get(12).getObjectId("_id")).append("discoId", nevermind.getObjectId("_id"))
            );
            incluirCollection.insertMany(incluirMusicas);

            // Relaciona disco Nevermind à banda Nirvana
            MongoCollection<Document> lancamentoCollection = database.getCollection("Lancamento");
            Document lancamento = new Document("discoId", nevermind.getObjectId("_id"))
                    .append("criadorId", nirvana.getObjectId("_id"));
            lancamentoCollection.insertOne(lancamento);

            System.out.println("Dados inseridos com sucesso");
        }
    }
}
