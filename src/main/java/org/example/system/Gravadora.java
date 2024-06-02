package org.example.system;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;

public class Gravadora {
    private final MongoDatabase database;

    public Gravadora() {
        database = getConnection();
    }

    private MongoDatabase getConnection() {
        String connectionString = "put here connection string";
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
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarProducoes() {
        MongoCollection<Document> produzirCollection = database.getCollection("Produzir");
        try (MongoCursor<Document> cursor = produzirCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarTocadas() {
        MongoCollection<Document> tocarCollection = database.getCollection("Tocar");
        try (MongoCursor<Document> cursor = tocarCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarLancamentos() {
        MongoCollection<Document> lancamentoCollection = database.getCollection("Lancamento");
        try (MongoCursor<Document> cursor = lancamentoCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarIntegracoes() {
        MongoCollection<Document> integrarCollection = database.getCollection("Integrar");
        try (MongoCursor<Document> cursor = integrarCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
    public void mostrarInclusoes() {
        MongoCollection<Document> incluirCollection = database.getCollection("Incluir");
        try (MongoCursor<Document> cursor = incluirCollection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
}
