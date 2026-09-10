package model;

/**
 * MODEL - so os dados de um estudante.
 *
 * Nao tem import nenhum. Nao sabe que existe tela nem banco.
 */
public class Animal {
	private String nome;
	private String especie;
	private Double tamanho;
	private Double peso;
	private String nascimento;
	private String dieta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {

// this.nome = o ATRIBUTO; nome sozinho = o PARAMETRO que chegou.
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;}
	public void setEspecie(String especie) {
		this.especie = especie;}

	public Double getTamanho() {
		return tamanho;}
	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;}

	public Double getPeso() {
		return peso;}
	public void setPeso(Double peso) {
		this.peso = peso;}

	public String getNascimento() {
		return nascimento;}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;}

	public String getDieta() {
		return dieta;}
	public void setDieta(String dieta) {
		this.dieta = dieta;}
	
	


}