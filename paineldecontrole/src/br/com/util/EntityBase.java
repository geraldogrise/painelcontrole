/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author santos
 */
public class EntityBase implements IEntityBase {

	public Object tag;
	public List<Campos_Join> join = new ArrayList<Campos_Join>();
	
	public List<Campos_Join> getJoin() {
		return join;
	}

	public void setJoin(List<Campos_Join> join) {
		this.join = join;
	}

	public Object colunaJoin(final String campo) {

		Object smallList = CollectionUtils.find(this.join,
				new org.apache.commons.collections.Predicate() {

					@Override
					public boolean evaluate(Object arg0) {
						Campos_Join c = (Campos_Join) arg0;
						return c.chave.equals(campo);
					}
				});

		if (smallList != null)
			return ((Campos_Join) smallList).valor;
		else
			return null;

	}

	public void Clear() {
		this.tag = null;
		this.join = new ArrayList<Campos_Join>();
	}

	@Override
	public Object getTag() {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void setTag(Object tag) {
		// TODO Auto-generated method stub
		this.tag = tag;
	}
}