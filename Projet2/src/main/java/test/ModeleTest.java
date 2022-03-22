package test;

import com.ensta.librarymanager.modele.*;
import java.time.LocalDate;

public class ModeleTest {
	public static void main(String args[]) {
		Livre l = new Livre(1, "Extension du domaine de la lutte", "Michel Houellebecq", "666");
		System.out.println(l.toString());
		Emprunt e = new Emprunt(2, 24, 666, LocalDate.now(), LocalDate.now());
		System.out.println(e.toString());
		Membre m = new Membre(5, "Gless", "Bastien", "je sais pas", "ncen", "666", Abonnement.VIP);
		System.out.println(m.toString());
	}

}
