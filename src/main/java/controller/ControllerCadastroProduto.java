/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import model.bo.Produto;
import utilities.Utilities;
import view.BuscaProduto;
import view.CadastroProduto;

/**
 *
 * @author Thiago
 */
public class ControllerCadastroProduto implements ActionListener {

    CadastroProduto cadastroProduto;
    public static int codigo;

    FocusListener focusDescription = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            Utilities.turnTextFieldGray(cadastroProduto.getjTextFieldDescricao());
        }

        @Override
        public void focusLost(FocusEvent e) {
            Utilities.turnTextFieldRed(cadastroProduto.getjTextFieldDescricao());
        }
    };

    FocusListener focusCodBarras = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            Utilities.turnTextFieldGray(cadastroProduto.getjTextFieldCodigoBarras());
        }

        @Override
        public void focusLost(FocusEvent e) {
            Utilities.turnTextFieldRed(cadastroProduto.getjTextFieldCodigoBarras());
        }
    };

    FocusListener focusPrice = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            Utilities.turnPriceTextFieldGray(cadastroProduto.getjTextFieldPreco());
        }

        @Override
        public void focusLost(FocusEvent e) {
            Utilities.turnPriceTextFieldRed(cadastroProduto.getjTextFieldPreco());
        }
    };

    public ControllerCadastroProduto(CadastroProduto cadastroProduto) {
        this.cadastroProduto = cadastroProduto;

        this.cadastroProduto.getjButtonNovo().addActionListener(this);
        this.cadastroProduto.getjButtonSair().addActionListener(this);
        this.cadastroProduto.getjButtonCancelar().addActionListener(this);
        this.cadastroProduto.getjButtonSalvar().addActionListener(this);
        this.cadastroProduto.getjButtonConsultar().addActionListener(this);

        this.cadastroProduto.getjTextFieldDescricao().addFocusListener(focusDescription);
        this.cadastroProduto.getjTextFieldCodigoBarras().addFocusListener(focusCodBarras);
        this.cadastroProduto.getjTextFieldPreco().addFocusListener(focusPrice);
        
        utilities.Utilities.ativa(true, this.cadastroProduto.getjPanelBotoes());
        utilities.Utilities.limpaComponentes(false, this.cadastroProduto.getjPanelDados());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cadastroProduto.getjButtonNovo()) {
            utilities.Utilities.ativa(false, this.cadastroProduto.getjPanelBotoes());
            utilities.Utilities.limpaComponentes(true, this.cadastroProduto.getjPanelDados());
            //Deseligando o ID no braço, PROVISÓRIO
            this.cadastroProduto.getjTextFieldId().setEditable(false);
            //Trazendo proximo id
            this.cadastroProduto.getjTextFieldDescricao().requestFocus();

        } else if (e.getSource() == this.cadastroProduto.getjButtonSair()) {
            this.cadastroProduto.dispose();

        } else if (e.getSource() == this.cadastroProduto.getjButtonCancelar()) {
            utilities.Utilities.ativa(true, this.cadastroProduto.getjPanelBotoes());
            utilities.Utilities.limpaComponentes(false, this.cadastroProduto.getjPanelDados());

        } else if (e.getSource() == this.cadastroProduto.getjButtonSalvar()) {

            Produto produto = new Produto();
            produto.setDescricao(this.cadastroProduto.getjTextFieldDescricao().getText());
            produto.setCodigoBarra(this.cadastroProduto.getjTextFieldCodigoBarras().getText());
            produto.setPreco(Float.parseFloat(this.cadastroProduto.getjTextFieldPreco().getText()));
            produto.setStatus(this.cadastroProduto.getjCheckBoxStatus().isSelected());

            if (this.cadastroProduto.getjTextFieldId().getText().equalsIgnoreCase("")) {
                service.ProdutoService.adicionar(produto);
            } else {
                produto.setId(Integer.parseInt(this.cadastroProduto.getjTextFieldId().getText()));
                service.ProdutoService.atualizar(produto);
            }

            utilities.Utilities.ativa(true, cadastroProduto.getjPanelBotoes());
            utilities.Utilities.limpaComponentes(false, cadastroProduto.getjPanelDados());

        } else if (e.getSource() == this.cadastroProduto.getjButtonConsultar()) {
            codigo = 0;

            BuscaProduto buscaProduto = new BuscaProduto(null, true);
            ControllerBuscaProduto controllerBuscaProduto = new ControllerBuscaProduto(buscaProduto);

            //Inserir o controller da busca de produto
            buscaProduto.setVisible(true);

            if (codigo != 0) {
                Produto produto = new Produto();
                produto = service.ProdutoService.carregar(codigo);

                utilities.Utilities.ativa(false, cadastroProduto.getjPanelBotoes());
                utilities.Utilities.limpaComponentes(true, cadastroProduto.getjPanelDados());

                this.cadastroProduto.getjTextFieldId().setText(produto.getId() + "");
                this.cadastroProduto.getjTextFieldDescricao().setText(produto.getDescricao() + "");
                this.cadastroProduto.getjTextFieldCodigoBarras().setText(produto.getCodigoBarra() + "");
                this.cadastroProduto.getjTextFieldPreco().setText(produto.getPreco() + "");

                if (produto.getStatus() == 'I') {
                    this.cadastroProduto.getjCheckBoxStatus().setSelected(true);
                } else {
                    this.cadastroProduto.getjCheckBoxStatus().setSelected(false);
                }

                this.cadastroProduto.getjTextFieldId().setEditable(false);
            }

        }
    }

}
