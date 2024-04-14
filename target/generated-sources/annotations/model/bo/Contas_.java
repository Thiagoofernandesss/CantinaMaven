package model.bo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Compra;
import model.bo.Venda;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-14T12:29:39")
@StaticMetamodel(Contas.class)
public class Contas_ { 

    public static volatile SingularAttribute<Contas, Date> dataHoraEmissao;
    public static volatile SingularAttribute<Contas, Compra> compra;
    public static volatile SingularAttribute<Contas, Float> observacao;
    public static volatile SingularAttribute<Contas, Float> valorDesconto;
    public static volatile SingularAttribute<Contas, Float> valorAcrescimo;
    public static volatile SingularAttribute<Contas, Float> valorQuitado;
    public static volatile SingularAttribute<Contas, Date> dataQuitação;
    public static volatile SingularAttribute<Contas, Venda> venda;
    public static volatile SingularAttribute<Contas, Date> dataVencimento;
    public static volatile SingularAttribute<Contas, Integer> id;
    public static volatile SingularAttribute<Contas, Character> flagTipoConta;
    public static volatile SingularAttribute<Contas, Float> valorEmitido;
    public static volatile SingularAttribute<Contas, Character> status;

}