/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author santos
 */
public class ConvertFW<T> {

	public List<T> ConvertResultSetToList(Class<T> c, ResultSet dados)
			throws Exception {

		Object[] meuCampos = null;
		HashMap<String, TrataCampos> dictMap = new HashMap<String, TrataCampos>();

		List<T> lista = new ArrayList<T>();

		java.sql.ResultSetMetaData metadata = (java.sql.ResultSetMetaData) dados
				.getMetaData();

		int qtd = metadata.getColumnCount();
		List<Field> fields = null;

		T objetoTeste = (T) c.newInstance();
		fields = Arrays.asList(objetoTeste.getClass().getDeclaredFields());
		for (int i = 0; i < qtd; i++) {
			String coluna = metadata.getColumnLabel(i + 1).toLowerCase();
			dictMap.put(coluna, new TrataCampos(coluna, "N"));
		}
		for (int i = 0; i < fields.size(); i++) {
			if (dictMap.containsKey(fields.get(i).getName()))
				dictMap.put(fields.get(i).getName(), new TrataCampos(fields
						.get(i).getName(), "S"));
		}

		meuCampos = dictMap.values().toArray();

		while (dados.next()) {

			T objeto = (T) c.newInstance();

			for (int i = 0; i < meuCampos.length; i++) {
				TrataCampos vampotrab = (TrataCampos) meuCampos[i];
				if (vampotrab.getisEntity().equals("S")) {

					Field campo = objeto.getClass().getDeclaredField(
							vampotrab.getColuna());

					campo.setAccessible(true);
					campo.set(objeto, dados.getObject(vampotrab.getColuna()));
				} else {

					((EntityBase) objeto).join.add(new Campos_Join(vampotrab
							.getColuna(),
							dados.getObject(vampotrab.getColuna())));
				}
			}
			lista.add((T) objeto);
		}

		return lista;
	}

	public static ResultSet getResult(String sql) throws Exception {
		ResultSet rs = null;
		Statement stm = null;
		stm = Conexao.getConnection().createStatement();
		rs = stm.executeQuery(sql);
		return rs;
	}

}
