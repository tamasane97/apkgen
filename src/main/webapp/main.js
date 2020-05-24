function marg1(){
	var slider=document.querySelector('#slider');
	slider.style.marginleft='100%';
	function marg2(){
		var slider=document.querySelector('#slider');
		slider.style.marginleft='200%';
		function marg0(){
			var slider=document.querySelector('#slider');
			slider.style.marginleft='0';
			setTimeout(marg1,8000);
		}
		setTimeout(marg0,8000);
	}
	setTimeout(marg2,8000);
}
 setInterval(slide,7500);
 setInterval(para,7200);
 setInterval(head,7200);
 setInterval(line,7300)
 setTimeout(marg1,8000);
 setInterval(slide2,8500);
 setInterval(head2,8700);
 setInterval(para2,8700);
 setInterval(line2,8600);


function slide(){
	var s=document.querySelectorAll('.slide');
	var i;
	for (i=0; i<s.length; i++) {
		s[i].style.tranform='scale(.8)';
	}
}
function slide2(){
	var s=document.querySelectorAll('.slide');
	var i;
	for (i=0; i<s.length; i++) {
		s[i].style.tranform='scale(1)';
	}
}

function head(){
	var h=document.querySelectorAll('h1');
	var i;
	for(i=0; i<h.length; i++){
		h[i].style.top='70px';
	}
}

function head2(){
	var h=document.querySelectorAll('h1');
	var i;
	for(i=0; i<h.length; i++){
		h[i].style.top='0';
	}
}

function para(){
	var p=document.querySelectorAll('p');
	var i;
	for(i=0; i<p.length; i++){
		p[i].style.bottom='120px';
	}
}
function para2(){
	var p=document.querySelectorAll('p');
	var i;
	for(i=0; i<p.length; i++){
		p[i].style.bottom='0';
	}
}
function line(){
 	var l=document.querySelector('.line');
 	var i;
	for(i=0; i<l.length; i++){
 		l[i].style.tranform='scale(0)';
    }
}
function line2(){
 	var l=document.querySelector('.line');
 	var i;
	for(i=0; i<l.length; i++){
 		l[i].style.tranform='scale(1)';
    }
}