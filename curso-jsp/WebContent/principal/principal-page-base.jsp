<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
                                        	<h1>Conte�do da p�gina - p�gina base</h1>
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
   
</body>

</html>
    