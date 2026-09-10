package modelo;

public class Animal {
	private int id;
	private String nome;
	private String especie;
	private double tamanho;
	private double peso;
	private String nascimento;
	private String dieta;

// Estudante NOVO: o id fica 0 porque quem gera o id e o banco.
	public Animal(String nome, String especie, double tamanho, double peso, String nascimento, String dieta) {
		this(0, nome, especie, tamanho, peso, nascimento, dieta);
	}

// Estudante que VEIO do banco: o id ja existe.
	public Animal(int id, String nome, String especie, double tamanho, double peso, String nascimento, String dieta) {
		this.id = id;
		this.nome = nome;
		this.especie = especie;
		this.tamanho = tamanho;
		this.peso = peso;
		this.nascimento = nascimento;
		this.dieta = dieta;
	}

	public int getId() {
		return id;}
	public void setId(int id) {
		this.id = id;}
	
	public String getNome() {
		return nome;}
	public void setNome(String nome) {
		this.nome = nome;}
	
	public double getTamanho() {
		return tamanho;}
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;}

	public double getPeso() {
		return peso;}
	public void setPeso(double peso) {
		this.peso = peso;}

	public String getNascimento() {
		return nascimento;}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;}

	public String getDieta() {
		return dieta;}
	public void setDieta(String dieta) {
		this.dieta = dieta;}

	public String getEspecie() {
		return especie;}
	public void setEspecie(String especie) {
		this.especie = especie;}

	public String toString() {
		return String.format("Animal[id=%d, nome=%s, dieta=%s, tamanho=%.2f, peso=%.2f, nascimento=%s, dieta=%s]", id, nome, especie, tamanho, peso, nascimento, dieta);
	}
}