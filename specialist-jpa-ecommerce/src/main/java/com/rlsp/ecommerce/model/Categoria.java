
package com.rlsp.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity

@Table(name= "categoria")
public class Categoria {


    
    /**
     * @GeneratedValue = Define uma ESTRATEGIA para definicao de chave primaria
     * 	- que faz a estrategia eh o propria implementacao da JPA no caso HIBERNATE
     * 
     * 	a) GenerationType.AUTO = o JPA que escolhe a estrategia (cria uma tabela para gerenciar os IDs)
     * 		- Cria um tabela chamada "hibernate_sequence" (SEM o "s")
     * 		Ex: @GeneratedValue(strategy = GenerationType.AUTO)
     * 	
     *  b) GenerationType.IDENTITY = pega a CHAVE PRIMARIA da coluna do DB que representa o ID (geralmente tem autoincremento no DB)
     *  	- MELHOR
     *   	- Utiliza o AUTOINCREMENT/PK do DB
     * 		
     *  c) GenerationType.SEQUENCE =  (NAO EXISTE NO MYSQL) 
     *  	Ex: @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia_categoria")
     *  			** generator = "sequencia_categoria" = usado para chamar o @SequenceGenerator
     *  
     *			@SequenceGenerator(name="sequencia_categoria", sequenceName = "sequencuas_chave_primaria, initialValue = 10")
     *				- traz as configuracoes do "Gerador de Sequencias"
     *				** generator = "sequencia_categoria" (Nome da tabela)
     *				** initialValue = 10 (valor inicial sera 10)
     *				** allocationsize = 100 (aloca na memoria o espaco de 100 IDs e pagara novamente mais 100, a partir de 101)
     *				** sequenceName = "sequencias_chave_primaria" (a sequencia quer organizara as chaves primarias)
     *  
     *  d) GenerationType.TABLE = cria uma tabela e guarda o ULTIMO identificador gerado
     * 	 - Cria um tabela chamada "hibernate_sequences" (COM o "s")
     *     Ex: @GeneratedValue(strategy = GenerationType.TABLE, generator= "tabela")
     *		   @TableGenerator(name = "tabela", 
     *							table = "hibernate_sequences",  = nome da tabela criada com as sequencias (para ter uma tabela para Entidade, cria-se com diferentes nomes
     *							pkColumnName = "sequence_name", = nome da chave primaria a ser criada
     *							pkColumnValue = "next_val",     = nome da coluna com o proximo valor a ser chamado 
     *							initialValue = 5, 				= valor inicial
     *							allocationSize = 30				= valor para ser gravado em memoria (como acima)
     *) 
     */
    //@GeneratedValue(strategy = GenerationType.AUTO)
    
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia_categoria")
    //@SequenceGenerator(name="sequencia_categoria", sequenceName = "sequencias_chave_primaria", initialValue = 10)
    
    //@GeneratedValue(strategy = GenerationType.TABLE, generator= "tabela")
    //@TableGenerator(name = "tabela", table = "novo_hibernate_sequences", pkColumnName = "categoria_seq_name", pkColumnValue = "categoria", initialValue = 5, allocationSize = 30)
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    /**
     * Serve para renomer a coluna no DB
     */
    @Column(name="categoria_pai_id")
    private Integer categoriaPaiId;

}