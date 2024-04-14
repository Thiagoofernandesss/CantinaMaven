package model.bo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Fornecedor;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-14T12:29:39")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, String> observacao;
    public static volatile SingularAttribute<Compra, Float> valorDesconto;
    public static volatile SingularAttribute<Compra, Character> flagTipoDesconto;
    public static volatile SingularAttribute<Compra, Date> dataHoraCompra;
    public static volatile SingularAttribute<Compra, Integer> id;
    public static volatile SingularAttribute<Compra, Integer> numeroNF;
    public static volatile SingularAttribute<Compra, Fornecedor> fornecedor;
    public static volatile SingularAttribute<Compra, Character> status;

}