package br.com.concorrencia;

import java.util.concurrent.Future;

public class ClienteLoja {

    public static void main(String[] args) {
        final Loja loja = new Loja("Loja da Maria");

        final long start = System.nanoTime();

        // Executa processo assincrono
        final Future<Double> precoFuturo = loja.getPrecoAssinc("Sapato 1");

        final long invocationTime = ((System.nanoTime() - start) / 1_000_000);

        System.out.println("A invocação retornou depois de " + invocationTime + " msecs");

        executarOutrosProcessos();

        // enquanto o preço do produto será calculado
        try {

            // Lê o preço do Future
            final double preco = precoFuturo.get();
            System.out.printf("Preço: %.2f%n", preco);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Preço retornado depois de " + retrievalTime + " msecs");
    }

    public static void executarOutrosProcessos() {
        System.out.println("Pesquisando em outras lojas ...");
    }
}
