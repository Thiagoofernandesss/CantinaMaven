/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.bo.Caixa;
import model.bo.Funcionario;
import service.FuncionarioService;
import view.BuscaCaixa;
import view.BuscaFuncionario;
import view.CadastroCaixa;
import view.CadastroFuncionario;

/**
 *
 * @author gabri
 */
public class ControllerCadastraCaixa implements ActionListener {

    private CadastroCaixa cadastroCaixa;
    public static int codigo;

    public ControllerCadastraCaixa(CadastroCaixa cadastroCaixa) {
        this.cadastroCaixa = cadastroCaixa;

        this.cadastroCaixa.getjButtonNovo().addActionListener(this);
        this.cadastroCaixa.getjButtonSair().addActionListener(this);
        this.cadastroCaixa.getjButtonCancelar().addActionListener(this);
        this.cadastroCaixa.getjButtonSalvar().addActionListener(this);
        this.cadastroCaixa.getjButtonConsultar().addActionListener(this);

        this.cadastroCaixa.getjButtonAdicionarFuncionario().addActionListener(this);
        this.cadastroCaixa.getjButtonPesquisarFuncionario().addActionListener(this);

        List<Funcionario> listaFuncionario = new ArrayList<>();
        listaFuncionario = service.FuncionarioService.carregar();

        utilities.Utilities.ativa(true, this.cadastroCaixa.getjPanelBotoes());
        utilities.Utilities.limpaComponentes(false, this.cadastroCaixa.getjPanelDados());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cadastroCaixa.getjButtonNovo()) {
            utilities.Utilities.ativa(false, this.cadastroCaixa.getjPanelBotoes());
            utilities.Utilities.limpaComponentes(true, this.cadastroCaixa.getjPanelDados());

            this.cadastroCaixa.getjTextFieldNumCaixa().setEditable(false); //Desligando id no braço, provisório

        } else if (e.getSource() == this.cadastroCaixa.getjButtonSair()) {
            this.cadastroCaixa.dispose(); //Ação botão sair

        } else if (e.getSource() == this.cadastroCaixa.getjButtonCancelar()) {
            //Ações botão cancelar
            utilities.Utilities.ativa(true, this.cadastroCaixa.getjPanelBotoes());
            utilities.Utilities.limpaComponentes(false, this.cadastroCaixa.getjPanelDados());

        } else if (e.getSource() == this.cadastroCaixa.getjButtonSalvar()) {
            //Ação Botão Salvar
            Caixa caixa = new Caixa();

            caixa.setObservaccao(this.cadastroCaixa.getjTextFieldObservacao().getText());
            caixa.setValorAbertura(Float.parseFloat(this.cadastroCaixa.getjFTFTSaldoInicial().getText()));
            caixa.setValorFechamento(Float.parseFloat(this.cadastroCaixa.getjFTFSaldoFinal().getText()));
            caixa.setStatu(this.cadastroCaixa.getjCheckBoxStatusCaixa().isSelected());

            String dataHoraAbertura = this.cadastroCaixa.getjFTFDataAbertura().getText();
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy/MM/dd");

            try {
                java.util.Date dataAbertura = formatoEntrada.parse(dataHoraAbertura);
                String dataAberturaFormatada = formatoSaida.format(dataAbertura); // Formatar a data no formato esperado pelo banco

                java.util.Date dataAberturaFormatadaDate = formatoSaida.parse(dataAberturaFormatada); // Convertendo de String para Date

                caixa.setDataHoraAbertura(dataAberturaFormatadaDate); // Passando a data formatada para setDataHoraAbertura
            } catch (ParseException m) {
                // Lida com erros de formatação da data, se necessário
            }

            String dataHoraFechamento = this.cadastroCaixa.getjFTFDataFechamento().getText();


            try {
                java.util.Date dataFechamento = formatoEntrada.parse(dataHoraFechamento);
                String dataFechamentoFormatada = formatoSaida.format(dataFechamento);
                
                java.util.Date dataFechamentoFormatadaDate = formatoSaida.parse(dataFechamentoFormatada);

                caixa.setDataHoraFechamento(dataFechamentoFormatadaDate);
            } catch (ParseException m) {
                // Lida com erros de formatação da data, se necessário
            }

            Funcionario funcionario = FuncionarioService.carregar("", this.cadastroCaixa.getjTextFieldFiltrarFuncionario().getText()).get(0);

            caixa.setFuncionario(funcionario);

            if (codigo == 0) {
                service.CaixaService.adicionar(caixa);
                utilities.Utilities.ativa(true, cadastroCaixa.getjPanelBotoes());
                utilities.Utilities.limpaComponentes(false, cadastroCaixa.getjPanelDados());

                this.cadastroCaixa.getjTextFieldNumCaixa().setEditable(false);
                this.cadastroCaixa.getjTextFieldFiltrarFuncionario().setEditable(false);

            } else {
                caixa.setId(codigo);

                service.CaixaService.atualizar(caixa);
                utilities.Utilities.ativa(true, this.cadastroCaixa.getjPanelBotoes());
                utilities.Utilities.limpaComponentes(false, this.cadastroCaixa.getjPanelDados());

                this.cadastroCaixa.getjTextFieldNumCaixa().setEditable(false);
                this.cadastroCaixa.getjTextFieldFiltrarFuncionario().setEditable(false);

            }

        } else if (e.getSource() == this.cadastroCaixa.getjButtonConsultar()) {
            codigo = 0;

            BuscaCaixa buscaCaixa = new BuscaCaixa(null, true);
            ControllerBuscaCaixa ControllerBuscaCaixa = new ControllerBuscaCaixa(buscaCaixa);
            //Inserir o controller da busca de cliente
            buscaCaixa.setVisible(true);

            if (codigo != 0) {

                utilities.Utilities.ativa(false, cadastroCaixa.getjPanelBotoes());
                utilities.Utilities.limpaComponentes(true, cadastroCaixa.getjPanelDados());

                Caixa caixa = new Caixa();
                caixa = service.CaixaService.carregar(codigo);

                this.cadastroCaixa.getjTextFieldObservacao().setText(caixa.getObservaccao() + "");
                this.cadastroCaixa.getjFTFTSaldoInicial().setText(caixa.getValorAbertura() + "");
                this.cadastroCaixa.getjFTFSaldoFinal().setText(caixa.getValorFechamento() + "");

                java.util.Date dataHoraAberturaBanco = caixa.getDataHoraAbertura();
                SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");

                //java.util.Date dataAbertura = formatoAberturaEntrada.parse(dataHoraAberturaBanco);
                String dataAberturaFormatada = formatoSaida.format(dataHoraAberturaBanco);
                this.cadastroCaixa.getjFTFDataAbertura().setText(dataAberturaFormatada);

                java.util.Date dataHoraFechamentoBanco = caixa.getDataHoraFechamento();

                //java.util.Date dataFechamento = formatoEntrada.parse(dataHoraFechamentoBanco);
                String dataFechamentoFormatada = formatoSaida.format(dataHoraFechamentoBanco);
                this.cadastroCaixa.getjFTFDataFechamento().setText(dataFechamentoFormatada);

                this.cadastroCaixa.getjTextFieldFiltrarFuncionario().setText(caixa.getFuncionario().getId() + "");

                if (caixa.getStatus() == 'I') {
                    this.cadastroCaixa.getjCheckBoxStatusCaixa().setSelected(true);
                }

                this.cadastroCaixa.getjTextFieldNumCaixa().setEditable(false);

            }

        } else if (e.getSource() == this.cadastroCaixa.getjButtonPesquisarFuncionario()) {

            buscarFuncionario();

        } else if (e.getSource() == this.cadastroCaixa.getjButtonAdicionarFuncionario()) {
            CadastroFuncionario cadastroFuncionario = new CadastroFuncionario();
            ControllerCadastroFuncionario controllerCadastroFuncionario = new ControllerCadastroFuncionario(cadastroFuncionario);
            cadastroFuncionario.setVisible(true);

        }
    }

    public Funcionario getFuncioanrioEndById(int idFuncionarioEnd) {
        for (Funcionario funcionarioAtual : service.FuncionarioService.carregar()) {
            if (funcionarioAtual.getId() == idFuncionarioEnd) {
                return funcionarioAtual;
            }

        }
        return null;
    }

    private void buscarFuncionario() {
        BuscaFuncionario buscaFuncionario = new BuscaFuncionario(null, true);
        ControllerBuscaFuncionario controllerBuscaFuncionario = new ControllerBuscaFuncionario(buscaFuncionario);
        buscaFuncionario.setVisible(true);

        if (ControllerCadastroFuncionario.codigo != 0) {
            Funcionario funcionario = getFuncioanrioEndById(ControllerCadastroFuncionario.codigo);
            this.cadastroCaixa.getjTextFieldFiltrarFuncionario().setText(funcionario.getNome());
        }
    }
}
