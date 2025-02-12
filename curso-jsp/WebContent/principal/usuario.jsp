<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Configura��o JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



    <!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <!-- Pre-loader start -->
  
  <jsp:include page="theme-loader.jsp"></jsp:include>
  
  <!-- Pre-loader end -->
  
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <!--  star navbar-->
         	<jsp:include page="navbar.jsp"></jsp:include>		
          <!--  end navbar-->
          
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
   				
   				<!-- star navbarmainmenu  -->              
       			  <jsp:include page="navbarmainmenu.jsp"></jsp:include> 
       			<!-- star navbarmainmenu  -->              
       			 
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                  
                                        
                                        	<div class="row">
                                            	<div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                	<div class="card">                                                                                                     	
                                                    	<div class="card-block">	                                                        
	                                                        <h4 class="sub-title">Cad. Usu�rio</h4>				                                   
				                                        	<form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
	                                                            
	                                                            <!-- tag utilizada para excluir usu�rio -->
	                                                            <input type="hidden" name="acao" id="acao" value="">
	                                                            
	                                                            <div class="form-group form-default form-static-label">
	                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Nome</label>
	                                                            </div>
	                                                            <div class="form-group form-default form-static-label">
	                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modelLogin.email}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Email</label>
	                                                            </div>
	                                                             <div class="form-group form-default form-static-label">
	                                                                <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${modelLogin.login}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Login</label>
	                                                            </div>
	                                                            <div class="form-group form-default form-static-label">
	                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modelLogin.senha}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">Senha</label>
	                                                            </div>
	                                                            <div class="form-group form-default form-static-label">
	                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">ID</label>
	                                                            </div>
	                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
	                                                             <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
	                                                             <button type="button" class="btn btn-info waves-effect waves-light" onclick="criarDelete();">Excluir</button>	
	                                                             <!-- Button trigger modal -->
																 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Pesquisar</button>									       
														         <!-- <button type="button" class="btn btn-info waves-effect waves-light" onclick="criarDeleteComAjax();">Excluir</button> -->														    													       	                                                    
                                                       		</form>                                       				
                                        				</div>
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<span id="msg">${msg}</span>
                                        	
                                        	<div style="height: 300px; overflow: scroll;">
					
												<table class="table" id="tabelaresultadosview">
												 
												  <thead>
												 
												    <tr>
												      <th scope="col">ID</th>
												      <th scope="col">Nome</th>
												      <th scope="col">Ver</th>
												    </tr>
												 
												  </thead>
												 	  <c:forEach items="${modelLogins}" var="ml">
												 	  	
												 	  		<tr>
												 	  			<td><c:out value="${ml.id}"></c:out> </td>
												 	  			<td><c:out value="${ml.nome}"></c:out> </td>
												 	  			<td><a class="btn btn-outline-secondary" href="<%= request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>												 	  			
												 	  		</tr>
												 	  	
												 	  	
												 	  </c:forEach>	
												  <tbody>
												    
												  </tbody>
										  		</table>	      					
										   
										   </div>
                                    
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Required Jquery -->
   	<jsp:include page="javascriptfile.jsp"></jsp:include>
   
   <!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisar Usu�rio</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	
		      	<div class="input-group mb-3">
	  				<input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" varia-describedby="basic-addon2">
	  				<div class="input-group-append">
	   	 				<button class="btn btn-outline-secondary" type="button" onclick="buscarUsuario();">Buscar</button>
	  				</div>
				</div>
				
				<div style="height: 300px; overflow: scroll;">
					
					<table class="table" id="tabelaresultados">
					  <thead>
					    <tr>
					      <th scope="col">ID</th>
					      <th scope="col">Nome</th>
					      <th scope="col">Ver</th>
					    </tr>
					  </thead>
					  <tbody>
					    
					  </tbody>
			  		</table>	      					
				</div>
				
				<span id="totalResultados"></span>				
			
	  </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
   
   <script type="text/javascript">
   
   		/*Fun��o usada para cria��o de um novo usu�rio*/
   		/*Vai limpar os campos do formul�rio*/
   		function limparForm() {
			
   			var elementos = document.getElementById("formUser").elements; /*retorna os elementos html dentro do form*/
   			
   			for(p = 0; p < elementos.length; p++){
   				elementos[p].value = '';
   			}
   			
		}
   		
   		function criarDeleteComAjax(){
   			
   			if(confirm('Deseja Confirmar Exclus�o ?')){
   				
   				var urlAction = document.getElementById('formUser').action;
   				var idUser = document.getElemetById('id').value;
 
   				
   				$.ajax({
   					
   					method: "get",
   					url : urlAction,
   					data : "id=" + idUser + '&acao=deletarajax',
   					success: function (response) {
   						limparForm();
   						document.getElementById('msg').textContent = response;
   						
   					}
   					
   				}).fail(function(xhr, status, errorThrown){
   					alert('Erro ao deletar usu�rio por id: ' + xhr.responseText);  
   				});
   				
   			}
   			
   		}
   		
   		
   		
   		function criarDelete() {
			if(confirm('Confirmar Exclus�o ?')){
	   			document.getElementById("formUser").method = 'get';
	   			document.getElementById("acao").value = 'deletar';
	   			document.getElementById("formUser").submit();
			}		
		}
   		
   		function buscarUsuario() {
			
   			var nomeBusca = document.getElementById('nomeBusca').value;
   			
   			if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){ //validando que tem q ter valor para buscar no banco de dados
   			
   				var urlAction = document.getElementById('formUser').action;
   				
				$.ajax({
   					
	   					method: "get",
	   					url : urlAction,
	   					data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
	   					success: function (response) {
	   						
	   						var json = JSON.parse(response);
	   						
	   						//jquery
	   						$('#tabelaresultados > tbody > tr').remove();
	   						
	   						for (var p = 0; p < json.length; p++) {
								
	   							$('#tabelaresultados > tbody').append('<tr> <td>'+ json[p].id +'</td> <td>'+ json[p].nome+ '</td> <td> <button type="button" onclick="verEditar('+json[p].id+')" class="btn btn-info">Ver</button> </td> </tr>')
	   							
							}
	   						
	   						document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
	   					}
	   					
	   				}).fail(function(xhr, status, errorThrown){
	   					alert('Erro ao buscar usu�rio por nome: ' + xhr.responseText);  
   				});
   				
   		   }
   			
	}
   		
   	function verEditar(id){
   	
   		var urlAction = document.getElementById('formUser').action;
   		
   		// utilizado o m�todo GET para envios de parametros
   		window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
   		
   	}
   
   </script>
   
</body>

</html>
    
    
    
    
    
    
    
    