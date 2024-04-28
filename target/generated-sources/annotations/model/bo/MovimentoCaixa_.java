package model.bo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Caixa;
import model.bo.Contas;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-28T01:31:35")
@StaticMetamodel(MovimentoCaixa.class)
public class MovimentoCaixa_ { 

    public static volatile SingularAttribute<MovimentoCaixa, Character> flagTipoMovimento;
    public static volatile SingularAttribute<MovimentoCaixa, Date> dataHorarioMovimento;
    public static volatile SingularAttribute<MovimentoCaixa, String> observacao;
    public static volatile SingularAttribute<MovimentoCaixa, Caixa> caixa;
    public static volatile SingularAttribute<MovimentoCaixa, Integer> id;
    public static volatile SingularAttribute<MovimentoCaixa, Float> valorMovimento;
    public static volatile SingularAttribute<MovimentoCaixa, Contas> contas;
    public static volatile SingularAttribute<MovimentoCaixa, Character> status;

}