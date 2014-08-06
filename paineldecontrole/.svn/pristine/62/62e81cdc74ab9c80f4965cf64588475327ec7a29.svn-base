package br.com.painelcontrole.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.business.GenericBusiness;
import br.com.model.Tab_fun;

public class ADNUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService {

	public Tab_fun getUsuarioByOpenId( Tab_fun model ) throws Exception {
		
		
		
		
		List<Tab_fun>  _listausuario = null;
		Tab_fun _usaurioretorno = null;
		GenericBusiness business = new GenericBusiness();
		try {
			_listausuario = business.consultarXML(null, model, "selectFiltro");
			 if ( _listausuario.size() > 0 ) 
			 {
				 _usaurioretorno = _listausuario.get(0);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _usaurioretorno;
	}
	
    public List<GrantedAuthority> getPermissoesById( Tab_fun model ) throws Exception {
		
		List<Tab_fun>  listapermissoes = null;
		
		String permissoes = "";
		String permissaopai = "";
		GenericBusiness business = new GenericBusiness();
	    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        //authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		try {
			listapermissoes = business.consultarXML(null, model, "permissoesById");
			
			 if ( listapermissoes.size() > 0 ) 
			 {
				  authList = new ArrayList<GrantedAuthority>(listapermissoes.size());
				 for( int posicao = 0 ; posicao < listapermissoes.size() ; posicao ++ ){
					 
					 if ( permissaopai.equals("") )
						 authList.add(new GrantedAuthorityImpl("ROLE_USER"));
					 if ( !permissaopai.equals(listapermissoes.get(posicao).getJoin().get(0).getValor().toString()) ){
						 authList.add(new GrantedAuthorityImpl(listapermissoes.get(posicao).getJoin().get(0).getValor().toString()));
						 permissaopai = listapermissoes.get(posicao).getJoin().get(0).getValor().toString();
					 } 
					 authList.add(new GrantedAuthorityImpl(listapermissoes.get(posicao).getJoin().get(1).getValor().toString()));
				 }
			 }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return authList;
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	
   	
       
    	UserICF user = null;
    	Tab_fun _usuario = new Tab_fun();
    	String _Permissoes = "";
    	_usuario.setFun_openid(username);
    	
    	try {
    		_usuario = getUsuarioByOpenId(_usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if ( _usuario != null ) {
           user = new UserICF( _usuario.getFun_nm(), "secret", true, false, false, false, getAuthorities(_usuario));
           user.setFun_cd(_usuario.getFun_cd());
           user.setFun_nm(_usuario.getFun_nm());
           user.setFun_email(_usuario.getFun_email());
           user.setFun_nmguerra(_usuario.getFun_nmguerra());
           user.setFun_situacao(_usuario.getFun_situacao());
           
    	} else {
    		
    		   throw new BadCredentialsException("Bad Credentials");
    		   
    	}
    	
        return user;
    }
    
    
 
    public  Collection<? extends GrantedAuthority> getAuthoritiesByNome(String nmusuario) {
    	
    	Tab_fun _usuario = new Tab_fun();
    	_usuario.setFun_openid(nmusuario);
    	return getAuthorities(_usuario);
    }
    
    
    private Collection<? extends GrantedAuthority> getAuthorities(Tab_fun model) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		try {
			authList = this.getPermissoesById(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return authList;
    }

	@Override
	public UserDetails loadUserDetails(Authentication arg0)
			throws UsernameNotFoundException {
		
	
		// TODO Auto-generated method stub
		return null;
	}
 
    

}