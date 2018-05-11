package br.com.concorrencia;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Loja {

    private final Random rnd = new Random();
    private final String nome;

    public Loja(final String nome) {
        this.nome = nome;
    }

    public double getPreco(final String produto) {
        return calculaPreco(produto);
    }

    public String getNome() {
        return nome;
    }

    public Future<Double> getPrecoAssinc(final String produto) {

        // Vai guardar o resultado da computação
        final CompletableFuture<Double> precoFuturo = new CompletableFuture<>();

        new Thread(() -> {

            // Executa a computação assíncronamente em outro Thread
            double preco = calculaPreco(produto);

            // Faz o set do valor computado no Future quando este ficar disponível
            precoFuturo.complete(preco);
        }).start();

        // retorna o Future sem ter de esperar pelo fim da computação
        return precoFuturo;
    }

    private double calculaPreco(final String produto) {
        delay();
        return rnd.nextDouble();
    }

    private void delay() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {}
    }
}
