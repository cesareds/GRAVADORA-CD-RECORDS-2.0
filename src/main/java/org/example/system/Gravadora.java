package org.example.system;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Gravadora {
    private final MongoDatabase database;
    private String db_conn = new String();

    public Gravadora() {
        database = getConnection();
    }

    private String getConfig() {
        String filePath = "config.properties";
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);

        } catch( IOException ex) {
            ex.printStackTrace();
        }
        return properties.getProperty("DB_CONNECTION");
    }

    private MongoDatabase getConnection() {
        db_conn = getConfig();
        String connectionString = db_conn;
        MongoClient mongoClient = MongoClients.create(connectionString);
        return mongoClient.getDatabase("CDRecords");
    }

    // Inserções
    public ObjectId musicoADD(String nome, String descricao, String genero, String cep, String rua, String cidade, String estado, String telefone) {
        MongoCollection<Document> musicoCollection = database.getCollection("Musico");
        Document musico = new Document("nome", nome)
                .append("descricao", descricao)
                .append("genero", genero)
                .append("cep", cep)
                .append("rua", rua)
                .append("cidade", cidade)
                .append("estado", estado)
                .append("telefone", telefone);
        musicoCollection.insertOne(musico);
        return musico.getObjectId("_id");
    }

    public ObjectId bandaADD(String nome, String descricao, String genero, String dataDeFormacao) {
        MongoCollection<Document> bandaCollection = database.getCollection("Banda");
        Document banda = new Document("nome", nome)
                .append("descricao", descricao)
                .append("genero", genero)
                .append("dataDeFormacao", dataDeFormacao);
        bandaCollection.insertOne(banda);
        return banda.getObjectId("_id");
    }

    public ObjectId discoADD(String dataLancamento, float preco, int platinas, String titulo, String formato, String descricao, String genero) {
        MongoCollection<Document> discoCollection = database.getCollection("Disco");
        Document disco = new Document("dataLancamento", dataLancamento)
                .append("preco", preco)
                .append("platinas", platinas)
                .append("titulo", titulo)
                .append("formato", formato)
                .append("descricao", descricao)
                .append("genero", genero);
        discoCollection.insertOne(disco);
        return disco.getObjectId("_id");
    }

    public ObjectId musicaADD(float duracao, int faixa, String autores, String titulo, String letra) {
        MongoCollection<Document> musicaCollection = database.getCollection("Musica");
        Document musica = new Document("duracao", duracao)
                .append("faixa", faixa)
                .append("autores", autores)
                .append("titulo", titulo)
                .append("letra", letra);
        musicaCollection.insertOne(musica);
        return musica.getObjectId("_id");
    }

    public ObjectId produtorADD(String nome, String biografia) {
        MongoCollection<Document> produtorCollection = database.getCollection("Produtor");
        Document produtor = new Document("nome", nome)
                .append("biografia", biografia);
        produtorCollection.insertOne(produtor);
        return produtor.getObjectId("_id");
    }

    public ObjectId instrumentoADD(String marca, String tipo, String nome) {
        MongoCollection<Document> instrumentoCollection = database.getCollection("Instrumento");
        Document instrumento = new Document("marca", marca)
                .append("tipo", tipo)
                .append("nome", nome);
        instrumentoCollection.insertOne(instrumento);
        return instrumento.getObjectId("_id");
    }

    // Relacionamentos
    public void integrar(ObjectId musicoObjectID, ObjectId bandaObjectID) {
        MongoCollection<Document> integrarCollection = database.getCollection("Integrar");
        Document integracao = new Document("musicoId", musicoObjectID)
                .append("bandaId", bandaObjectID);
        integrarCollection.insertOne(integracao);
    }

    public void incluir(ObjectId musicaObjectID, ObjectId discoObjectID) {
        MongoCollection<Document> incluirCollection = database.getCollection("Incluir");
        Document inclusao = new Document("musicaId", musicaObjectID)
                .append("discoId", discoObjectID);
        incluirCollection.insertOne(inclusao);
    }

    public void lancar(ObjectId discoObjectID, ObjectId criadorObjectID) {
        MongoCollection<Document> lancamentoCollection = database.getCollection("Lancamento");
        Document lancamento = new Document("discoId", discoObjectID)
                .append("criadorId", criadorObjectID);
        lancamentoCollection.insertOne(lancamento);
    }

    public void produzir(ObjectId discoObjectID, ObjectId produtorObjectID) {
        MongoCollection<Document> produzirCollection = database.getCollection("Produzir");
        Document producao = new Document("discoId", discoObjectID)
                .append("produtorId", produtorObjectID);
        produzirCollection.insertOne(producao);
    }
    public void participar(ObjectId musicaObjectID, ObjectId criadorObjectID) {
        MongoCollection<Document> participarCollection = database.getCollection("Participar");
        Document participacao = new Document("musicaId", musicaObjectID)
                .append("criadorId", criadorObjectID);
        participarCollection.insertOne(participacao);
    }

    public void tocar(ObjectId instrumentoObjectID, ObjectId musicoObjectID) {
        MongoCollection<Document> tocarCollection = database.getCollection("Tocar");
        Document toque = new Document("instrumentoId", instrumentoObjectID)
                .append("musicoId", musicoObjectID);
        tocarCollection.insertOne(toque);
    }
    // Exibição
    public void mostrarMusicos() {
        MongoCollection<Document> musicoCollection = database.getCollection("Musico");
        try (MongoCursor<Document> cursor = musicoCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarBandas() {
        MongoCollection<Document> bandaCollection = database.getCollection("Banda");
        try (MongoCursor<Document> cursor = bandaCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarDiscos() {
        MongoCollection<Document> discoCollection = database.getCollection("Disco");
        try (MongoCursor<Document> cursor = discoCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarMusicas() {
        MongoCollection<Document> musicaCollection = database.getCollection("Musica");
        try (MongoCursor<Document> cursor = musicaCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarProdutores() {
        MongoCollection<Document> produtorCollection = database.getCollection("Produtor");
        try (MongoCursor<Document> cursor = produtorCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarInstrumentos() {
        MongoCollection<Document> instrumentoCollection = database.getCollection("Instrumento");
        try (MongoCursor<Document> cursor = instrumentoCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarParticipacoes() {
        MongoCollection<Document> participarCollection = database.getCollection("Participar");
        try (MongoCursor<Document> cursor = participarCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Get Musica DB
                MongoCollection<Document> musicaCollection = database.getCollection("Musica");
                ObjectId musicaId = doc.getObjectId("musicaId");
                Document queryMusica = new Document("_id", musicaId);
                MongoCursor<Document> cursorMusica = musicaCollection.find(queryMusica).iterator();
                String nomeMusica = "";
                if (cursorMusica.hasNext()) {
                    Document docMusica = cursorMusica.next();
                    nomeMusica = docMusica.getString("titulo");
                }
                cursorMusica.close();

                // Get Musico/Banda DB
                MongoCollection<Document> musicoCollection = database.getCollection("Musico");
                MongoCollection<Document> bandaCollection = database.getCollection("Banda");
                ObjectId criadorId = doc.getObjectId("criadorId");
                Document queryCriador = new Document("_id", criadorId);
                MongoCursor<Document> cursorCriadorMusico = musicoCollection.find(queryCriador).iterator();
                MongoCursor<Document> cursorCriadorBanda = bandaCollection.find(queryCriador).iterator();
                String nomeCriador = "";
                if (cursorCriadorMusico.hasNext()) {
                    Document docCriador = cursorCriadorMusico.next();
                    nomeCriador = docCriador.getString("nome");
                } else if (cursorCriadorBanda.hasNext()) {
                    Document docCriador = cursorCriadorBanda.next();
                    nomeCriador = docCriador.getString("nome");
                }
                cursorCriadorMusico.close();
                cursorCriadorBanda.close();

                System.out.println("Musica:\t" + nomeMusica + " - Criador:\t" + nomeCriador);
            }
        }
    }


    public void mostrarProducoes() {
        MongoCollection<Document> produzirCollection = database.getCollection("Produzir");
        try (MongoCursor<Document> cursor = produzirCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Get Disco DB
                MongoCollection<Document> discoCollection = database.getCollection("Disco");
                ObjectId discoId = doc.getObjectId("discoId");
                Document queryDisco = new Document("_id", discoId);
                MongoCursor<Document> cursorDisco = discoCollection.find(queryDisco).iterator();
                String nomeDisco = "";
                if (cursorDisco.hasNext()) {
                    Document docDisco = cursorDisco.next();
                    nomeDisco = docDisco.getString("titulo");
                }
                cursorDisco.close();

                // Get Produtor DB
                MongoCollection<Document> produtorCollection = database.getCollection("Produtor");
                ObjectId produtorId = doc.getObjectId("produtorId");
                Document queryProdutor = new Document("_id", produtorId);
                MongoCursor<Document> cursorProdutor = produtorCollection.find(queryProdutor).iterator();
                String nomeProdutor = "";
                if (cursorProdutor.hasNext()) {
                    Document docProdutor = cursorProdutor.next();
                    nomeProdutor = docProdutor.getString("nome");
                }
                cursorProdutor.close();

                System.out.println("Disco:\t" + nomeDisco + " - Produtor:\t" + nomeProdutor);
            }
        }
    }

    public void mostrarTocadas() {
        MongoCollection<Document> tocarCollection = database.getCollection("Tocar");
        try (MongoCursor<Document> cursor = tocarCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Get Musico DB
                MongoCollection<Document> musicoCollection = database.getCollection("Musico");
                ObjectId musicoId = doc.getObjectId("musicoId");
                Document queryMusico = new Document("_id", musicoId);
                MongoCursor<Document> cursorMusico = musicoCollection.find(queryMusico).iterator();
                String nomeMusico = "";
                if (cursorMusico.hasNext()) {
                    Document docMusico = cursorMusico.next();
                    nomeMusico = docMusico.getString("nome");
                }
                cursorMusico.close();

                // Get Instrumento DB
                MongoCollection<Document> instrumentoCollection = database.getCollection("Instrumento");
                ObjectId instrumentoId = doc.getObjectId("instrumentoId");
                Document queryInstrumento = new Document("_id", instrumentoId);
                MongoCursor<Document> cursorInstrumento = instrumentoCollection.find(queryInstrumento).iterator();
                String nomeInstrumento = "";
                if (cursorInstrumento.hasNext()) {
                    Document docInstrumento = cursorInstrumento.next();
                    nomeInstrumento = docInstrumento.getString("nome");
                }
                cursorInstrumento.close();

                System.out.println("Musico:\t" + nomeMusico + " - Instrumento:\t" + nomeInstrumento);
            }
        }
    }
    public void mostrarLancamentos() {
        MongoCollection<Document> lancamentoCollection = database.getCollection("Lancamento");
        try (MongoCursor<Document> cursor = lancamentoCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Get Disco DB
                MongoCollection<Document> discoCollection = database.getCollection("Disco");
                ObjectId discoId = doc.getObjectId("discoId");
                Document queryDisco = new Document("_id", discoId);
                MongoCursor<Document> cursorDisco = discoCollection.find(queryDisco).iterator();
                String nomeDisco = "";
                if (cursorDisco.hasNext()) {
                    Document docDisco = cursorDisco.next();
                    nomeDisco = docDisco.getString("titulo");
                }
                cursorDisco.close();

                // Get Criador (Musico or Banda) DB
                MongoCollection<Document> musicoCollection = database.getCollection("Musico");
                MongoCollection<Document> bandaCollection = database.getCollection("Banda");
                ObjectId criadorId = doc.getObjectId("criadorId");
                Document queryCriadorMusico = new Document("_id", criadorId);
                Document queryCriadorBanda = new Document("_id", criadorId);
                MongoCursor<Document> cursorCriadorMusico = musicoCollection.find(queryCriadorMusico).iterator();
                MongoCursor<Document> cursorCriadorBanda = bandaCollection.find(queryCriadorBanda).iterator();
                String nomeCriador = "";
                if (cursorCriadorMusico.hasNext()) {
                    Document docCriador = cursorCriadorMusico.next();
                    nomeCriador = docCriador.getString("nome");
                } else if (cursorCriadorBanda.hasNext()) {
                    Document docCriador = cursorCriadorBanda.next();
                    nomeCriador = docCriador.getString("nome");
                }
                cursorCriadorMusico.close();
                cursorCriadorBanda.close();

                System.out.println("Disco:\t" + nomeDisco + " - Criador:\t" + nomeCriador);
            }
        }
    }
    public void mostrarIntegracoes() {
        MongoCollection<Document> integrarCollection = database.getCollection("Integrar");
        try (MongoCursor<Document> cursor = integrarCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Get Musico DB
                MongoCollection<Document> musicoCollection = database.getCollection("Musico");
                ObjectId musicoId = doc.getObjectId("musicoId");
                Document queryMusico = new Document("_id", musicoId);
                MongoCursor<Document> cursorMusico = musicoCollection.find(queryMusico).iterator();
                String nomeMusico = "";
                if (cursorMusico.hasNext()) {
                    Document docMusico = cursorMusico.next();
                    nomeMusico = docMusico.getString("nome");
                }
                cursorMusico.close();

                // Get Banda DB
                MongoCollection<Document> bandaCollection = database.getCollection("Banda");
                ObjectId bandaId = doc.getObjectId("bandaId");
                Document queryBanda = new Document("_id", bandaId);
                MongoCursor<Document> cursorBanda = bandaCollection.find(queryBanda).iterator();
                String nomeBanda = "";
                if (cursorBanda.hasNext()) {
                    Document docBanda = cursorBanda.next();
                    nomeBanda = docBanda.getString("nome");
                }
                cursorBanda.close();

                System.out.println("Musico:\t" + nomeMusico + " - Banda:\t" + nomeBanda);
            }
        }
    }

    public void mostrarInclusoes() {
        MongoCollection<Document> incluirCollection = database.getCollection("Incluir");
        try (MongoCursor<Document> cursor = incluirCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Get Music DB
                MongoCollection<Document> incluirCollectionMusic = database.getCollection("Musica");
                ObjectId musicaId = doc.getObjectId("musicaId");
                Document queryMusica = new Document("_id", musicaId);
                MongoCursor<Document> cursorMusica = incluirCollectionMusic.find(queryMusica).iterator();
                String nomeMusica = "";
                if (cursorMusica.hasNext()) {
                    Document docMusica = cursorMusica.next();
                    nomeMusica = docMusica.getString("titulo");
                }
                cursorMusica.close();

                // Get Disco DB
                MongoCollection<Document> incluirCollectionDisco = database.getCollection("Disco");
                ObjectId discoId = doc.getObjectId("discoId");
                Document queryDisco = new Document("_id", discoId); // Get from Incluir DB
                MongoCursor<Document> cursorDisco = incluirCollectionDisco.find(queryDisco).iterator();
                String nomeDisco = "";
                if (cursorDisco.hasNext()) {
                    Document docDisco = cursorDisco.next();
                    nomeDisco = docDisco.getString("titulo");
                }
                cursorDisco.close();

                System.out.println("Musica:\t" + nomeMusica + " - Disco:\t" + nomeDisco);
            }
        }
    }
}
