# LibManager_Gless_IN205

Je n'ai pas terminé le projet.

Les DAOs sont implémentés.
Les Services sont implémentés.

Pour les Servlets, j'ai copié quasiment à 100% sur un camarade.
J'ai ensuite essayé de faire fonctionner les JSP, sans succès.
J'obtiens le message d'erreur suivant pour dashboard.jsp :

An error occurred at line: 27 in the jsp file: /WEB-INF/View/dashboard.jsp
req cannot be resolved
24:         <div class="col l4 s6">
25:           <div class="small-box bg-aqua">
26:             <div class="inner">
27:               <h3><%= req.getAttribute("nMembres") %></h3> <!-- TODO : afficher le nombre de membres � la place de 12 -->
28:               <p>Membres</p>
29:             </div>
30:             <div class="icon">

Néanmoins, je comprends la structure des différentes couches de l'application, et les grandes lignes du fonctionnement du JSP avec les servlets.