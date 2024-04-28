package model.bo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Carteirinha;
import model.bo.Funcionario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-28T01:31:35")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Date> datahoravenda;
    public static volatile SingularAttribute<Venda, String> observacao;
    public static volatile SingularAttribute<Venda, Float> valorDesconto;
    public static volatile SingularAttribute<Venda, Character> flagTipoDesconto;
    public static volatile SingularAttribute<Venda, Integer> id;
    public static volatile SingularAttribute<Venda, Funcionario> funcionario;
    public static volatile SingularAttribute<Venda, Carteirinha> carteirinha;
    public static volatile SingularAttribute<Venda, Character> status;

}