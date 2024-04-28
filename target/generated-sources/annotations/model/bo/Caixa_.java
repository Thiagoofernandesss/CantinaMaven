package model.bo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Funcionario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-27T23:22:45")
@StaticMetamodel(Caixa.class)
public class Caixa_ { 

    public static volatile SingularAttribute<Caixa, Float> valorFechamento;
    public static volatile SingularAttribute<Caixa, String> observacao;
    public static volatile SingularAttribute<Caixa, Date> dataHoraAbertura;
    public static volatile SingularAttribute<Caixa, Float> valorAbertura;
    public static volatile SingularAttribute<Caixa, Integer> id;
    public static volatile SingularAttribute<Caixa, Date> dataHoraFechamento;
    public static volatile SingularAttribute<Caixa, Funcionario> funcionario;
    public static volatile SingularAttribute<Caixa, Character> status;

}