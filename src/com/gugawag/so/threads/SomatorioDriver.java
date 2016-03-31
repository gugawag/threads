package com.gugawag.so.threads;

/**
 * This program creates a separate thread by implementing the Runnable interface.
 *
 * Figure 4.12
 *
 * @author Gagne, Galvin, Silberschatz
 * @author Gustavo Wagner, gugawag@gmail.com
 * Operating System Concepts  - Ninth Edition
 * Copyright John Wiley & Sons - 2013
 */


//Armazenada o valor do somatório
class Sum{
	private int sum;

	public int get() {
		return sum;
	}

	public void set(int sum) {
		this.sum = sum;
	}
}

//Thread que realiza o somatório
class Summation implements Runnable{
	private int inicio, fim;
	private Sum sumValue;

	public Summation(int inicio, int fim, Sum sumValue) {
		if (fim < 0)
			throw new IllegalArgumentException();

		this.fim = fim;
		this.inicio = inicio;
		this.sumValue = sumValue;
	}

	public void run() {
		int sum = 0;

		for (int i = inicio; i <= fim; i++)
			sum += i;

		sumValue.set(sum);
	}
}

//Programa que executa a thread do somatório
public class SomatorioDriver{
	public static void main(String[] args) {
		if (args.length != 4) {
			System.err.println("Use SomatorioDriver <integer> <integer>");
			System.exit(0);
		}

		Sum sumObject = new Sum();
		int inicio = Integer.parseInt(args[0]);
		int fim = Integer.parseInt(args[1]);

		Thread worker = new Thread(new Summation(inicio, fim, sumObject));
		worker.start();

		try {
			worker.join();
		} catch (InterruptedException ie) { }
		System.out.println("O somatório de " + inicio + " até " + fim + " é " + sumObject.get());
	}
}
