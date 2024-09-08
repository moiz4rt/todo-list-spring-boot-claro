package me.dio.moiz4rt.todo.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Data
public class Item
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private Long id;
   private String titulo;
   private String descricao;
}
