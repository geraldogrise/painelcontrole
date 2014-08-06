<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="ADNTags" uri="ADNFwTaglib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PAINEL DE CONTROLE</title>
<link href="../../painelcontrole/resources/css/geral.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="../../painelcontrole/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="../../painelcontrole/resources/js/geral.js"></script>
</head>
<body class="off">
	<div id="topo">

		<div id="topoSite">

			<a href="#" id="logoTopo"><img
				src="../../painelcontrole/resources/images/logoTopo.png" /></a>

			<div id="login">
				<c:url var="openIDLoginUrl" value="/j_spring_security_check" />
				<form action="${openIDLoginUrl}" method="post" name="form1"
					id="formLogin">
					<label> <span>Usuário</span> <input type="text"
						style="width: 200px" name="j_username" id="usernameField" value="" />
					</label> <label> <span>Senha</span> <input name="j_password"
						id="passwordField" type="password" value="" style="width: 100px" />
					</label> <input class="btnPadrao" value="Acessar" type="submit" />
					<div class="limpa"></div>
				</form>
			</div>
		</div>

	</div>



	<div style="text-align: center; padding: 50px 0 0 0">
		<img src="../../painelcontrole/resources/images/oi.png" width="1024" />
	</div>




</body>
</html>


