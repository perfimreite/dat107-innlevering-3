package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;

public class Main {
    public static void main(String[] args) {
        AnsattDAO ansattDAO = new AnsattDAO();
        System.out.println(ansattDAO.finnAnsattMedId(1).toString());
    }
}
