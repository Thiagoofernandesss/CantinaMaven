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
import model.bo.Caixa;
import view.BuscaCaixa;

/**
 *
 * @author gabri
 */
public class ControllerBuscaCaixa implements ActionListener {

    BuscaCaixa buscaCaixa;

    public ControllerBuscaCaixa(BuscaCaixa buscaCaixa) {
        this.buscaCaixa = buscaCaixa;
        this.buscaCaixa.getjButtonFiltrar().addActionListener(this);
        this.buscaCaixa.getjButtonCarregar().addActionListener(this);
        this.buscaCaixa.getjButtonSair().addActionListener(this);
        this.buscaCaixa.getjComboBoxBuscaCaixaPor().addActionListener(this);
        this.buscaCaixa.getjTextFieldFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buscaCaixa.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaCaixa.getjTableDados().getModel();
            tabela.setRowCount(0); // Limpa a tabela

            String filtro = this.buscaCaixa.getjTextFieldFiltrar().getText().trim();

            if (filtro.isEmpty()) {
                // Se o campo de filtro estiver vazio, exibe todos os clientes
                List<Caixa> listaCaixa = service.CaixaService.carregar();
                for (Caixa caixaAtual : listaCaixa) {
                    tabela.addRow(new Object[]{
                        caixaAtual.getId(),
                        caixaAtual.getDataHoraAbertura(),
                        caixaAtual.getFuncionario().getNome(),
                        caixaAtual.getObservaccao(),
                        caixaAtual.getStatus()
                    });
                }
            } else {
                // Se houver texto no campo de filtro, realiza a busca com base no critério selecionado
                List<Caixa> listaCaixa = new ArrayList<>();

                if (this.buscaCaixa.getjComboBoxBuscaCaixaPor().getSelectedItem().toString().equalsIgnoreCase("id")) {
                    try {
                        int id = Integer.parseInt(filtro);
                        listaCaixa.add(service.CaixaService.carregar(id));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                } else if (this.buscaCaixa.getjComboBoxBuscaCaixaPor().getSelectedItem().toString().equalsIgnoreCase("observação")) {
                    listaCaixa = service.CaixaService.carregar("observacao", this.buscaCaixa.getjTextFieldFiltrar().getText());
                } else if (this.buscaCaixa.getjComboBoxBuscaCaixaPor().getSelectedItem().toString().equalsIgnoreCase("status")) {
                    listaCaixa = service.CaixaService.carregar("status", this.buscaCaixa.getjTextFieldFiltrar().getText());
                } else {
                    listaCaixa = service.CaixaService.carregar(this.buscaCaixa.getjComboBoxBuscaCaixaPor().getSelectedItem().toString().trim().toLowerCase(),
                            this.buscaCaixa.getjTextFieldFiltrar().getText());
                }

                for (Caixa caixaAtual : listaCaixa) {
                    tabela.addRow(new Object[]{
                        caixaAtual.getId(),
                        caixaAtual.getDataHoraAbertura(),
                        caixaAtual.getFuncionario().getNome(),
                        caixaAtual.getObservaccao(),
                        caixaAtual.getStatus()
                    });
                }
            }
        } else if (e.getSource() == this.buscaCaixa.getjButtonCarregar()) {
            controller.ControllerCadastraCaixa.codigo = (int) this.buscaCaixa.getjTableDados().
                    getValueAt(this.buscaCaixa.getjTableDados().getSelectedRow(), 0);

            this.buscaCaixa.dispose();

        } else if (e.getSource()
                == this.buscaCaixa.getjButtonSair()) {
            this.buscaCaixa.dispose();

        }

    }
}
