package entities;

import java.util.Objects;

public class Senso {

	private int ano;
	private String estado;
	private String nome_mun;
	private double pib;
	private int populacao;
	private double pib_per_cap;
	
	public Senso() {	
	}

	public Senso(int ano, String estado, String nome_mun, double pib,
			int populacao, double pib_per_cap) {
		this.ano = ano;
		this.estado = estado;
		this.nome_mun = nome_mun;
		this.pib = pib;
		this.populacao = populacao;
		this.pib_per_cap = pib_per_cap;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNome_mun() {
		return nome_mun;
	}

	public void setNome_mun(String nome_mun) {
		this.nome_mun = nome_mun;
	}

	public double getPib() {
		return pib;
	}

	public void setPib(double pib) {
		this.pib = pib;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}

	public double getPib_per_cap() {
		return pib_per_cap;
	}

	public void setPib_per_cap(double pib_per_cap) {
		this.pib_per_cap = pib_per_cap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, nome_mun);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Senso other = (Senso) obj;
		return ano == other.ano && Objects.equals(nome_mun, other.nome_mun);
	}

	@Override
	public String toString() {
		return "Senso [ano=" + ano + ", estado=" + estado + ", nome_mun=" + nome_mun + ", pib=" + pib + ", populacao="
				+ populacao + ", pib_per_cap=" + pib_per_cap + "]";
	}
}
