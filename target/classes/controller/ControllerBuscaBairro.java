/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.Bairro;
import service.BairroService;
import view.BuscaBairro;

/**
 *
 * @author Thiago and Gabrieli
 */
public class ControllerBuscaBairro implements ActionListener {

    BuscaBairro buscaBairro;

    public ControllerBuscaBairro(BuscaBairro buscaBairro) {

        this.buscaBairro = buscaBairro;
        this.buscaBairro.getjButtonFiltrar().addActionListener(this);
        this.buscaBairro.getjButtonCarregar().addActionListener(this);
        this.buscaBairro.getjButtonSair().addActionListener(this);
        this.buscaBairro.getjComboBoxBuscaBairroPor().addActionListener(this);
        this.buscaBairro.getjTextFieldFiltrar().addActionListener(this);
        
    }

    @Override
 public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buscaBairro.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaBairro.getjTableDados().getModel();
            tabela.setRowCount(0);

            String filtro = this.buscaBairro.getjTextFieldFiltrar().getText().trim();

            if (filtro.isEmpty()) {
                List<Bairro> listaBairros = service.BairroService.carregar();

                for (Bairro bairroAtual : listaBairros) {
                    tabela.addRow(new Object[]{
                        bairroAtual.getId(),
                        bairroAtual.getDescricao()
                    });
                }

            } else {
                List<Bairro> listaBairros = new ArrayList<Bairro>();
                if ( this.buscaBairro.getjComboBoxBuscaBairroPor().getSelectedItem().toString().equalsIgnoreCase("id")) {
                    try {
                        int id = Integer.parseInt(filtro);
                        listaBairros.add(service.BairroService.carregar(id));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(this.buscaBairro.getjComboBoxBuscaBairroPor().getSelectedItem().toString().equalsIgnoreCase("descrição")){
                    listaBairros = service.BairroService.carregar("descricao", this.buscaBairro.getjTextFieldFiltrar().getText());
                }else{
                    listaBairros = service.BairroService.carregar(this.buscaBairro.getjComboBoxBuscaBairroPor()
                            .getSelectedItem().toString().trim().toLowerCase(), this.buscaBairro.getjTextFieldFiltrar().getText());
                }

                for (Bairro bairroAtual : listaBairros) {
                    tabela.addRow(new Object[]{
                        bairroAtual.getId(),
                        bairroAtual.getDescricao()
                    });
                }
            }

        } else if (e.getSource() == this.buscaBairro.getjButtonCarregar()) {
            controller.ControllerCadastroBairro.codigo = (int) this.buscaBairro.getjTableDados().
                    getValueAt(this.buscaBairro.getjTableDados().getSelectedRow(), 0);
            
            this.buscaBairro.dispose();

        } else if (e.getSource() == this.buscaBairro.getjButtonSair()) {
            this.buscaBairro.dispose();

        }
    }

}
