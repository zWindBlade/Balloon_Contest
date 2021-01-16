package entidades;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

	private int numeroEquipo;
	private List<String> globosAsignados;

	public Equipo(int numeroEquipo,  List<String> globosAsignados) {
		this.numeroEquipo = numeroEquipo;
		this.globosAsignados = globosAsignados;
	}

	public Equipo() {
		this.numeroEquipo = 0;
		this.globosAsignados = new ArrayList<>();
	}

	public int getNumeroEquipo() {
		return numeroEquipo;
	}

	public void setNumeroEquipo(int numeroEquipo) {
		this.numeroEquipo = numeroEquipo;
	}

	public List<String> getGlobosAsignados() {
		return globosAsignados;
	}

	public void setGlobosAsignados(List<String> globosAsignados) {
		this.globosAsignados = globosAsignados;
	}

	@Override
	public String toString() {
		return "Equipo [numeroEquipo=" + numeroEquipo + ", globosAsignados=" + globosAsignados + "]";
	}

	public int getNumeroDeGlobosAsignados(){
		return this.globosAsignados.size();
	}

}
