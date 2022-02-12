var firebaseConfig = {
    apiKey: "AIzaSyAbCRqWE9I7pvJcCpi1h3VaZ0nbnP_hMTA",
    authDomain: "impresion3d-sv.firebaseapp.com",
    databaseURL: "https://impresion3d-sv-default-rtdb.firebaseio.com",
    projectId: "impresion3d-sv",
    storageBucket: "impresion3d-sv.appspot.com",
    messagingSenderId: "99351081625",
    appId: "1:99351081625:web:d29d4d1ffbcc776d597e08",
    measurementId: "G-VMG1QRFM15"
  };
  firebase.initializeApp(firebaseConfig);
var products=JSON.parse(localStorage.getItem('cart'));
var cartItems=[];
var cart_n=document.getElementById('cart_n');
var table=document.getElementById("table");
var total=0;

function tableHTML(i){
    return `
     <td>
        <div class="cart-info" >
                    <img src="${products[i].Url}"  >
                        <div>
                            <p>${products[i].Nombre}</p>
                            <small> <p>Precio:  $ ${products[i].Precio}</p></small>
                            <br>
                            <a href="">Remove</a>
                        </div>
        </div>
        <td style="text-align:left">1</td>
        <td style="text-align:left"> $${products[i].Precio}</td>
     </td>
    `;
}




function buy(){
    var d=new Date();
    var t =d.getTime();
    var counter=t;
    counter+=1;
    let db=firebase.database().ref("Orden/"+d);
    var nombre=document.getElementById('nombre').value;
    var tel=document.getElementById('tel').value;
    var direccion=document.getElementById('direccion').value;

    let itemdb={
        ID_Orden:counter,
        Cliente: nombre,
        Numero_de_Telefono:tel,
        Direccion:direccion,
        _Productos:products,
        _Total:`$`+total
    }

    db.set(itemdb);
   Swal.fire(
  'Para confirmar su orden se enviara un mensaje atraves de whatsapp',
  'Su numero de orden es:'+`${itemdb.ID_Orden}`,
  'success'
)
   clean();

}
function clean(){
    localStorage.clear();
    for(let index=0; index<products.length; index++){
        table.innerHTML+=tableHTML(index);
        total=total+parseInt(products[index].Precio);
    }
    total=0;
    table.innerHTML=`
    <tr></tr>
    `;
    cart_n.innerHTML='';
    document.getElementById("btnBuy").style.display="none";
    document.getElementById("btnClean").style.display="none";

}
function render(){
    for(let index=0; index< products.length; index++){
        table.innerHTML+=tableHTML(index);
        total=total+parseInt(products[index].Precio);
    }
    table.innerHTML+=`
    <div class="total-price" >
        <table  >
           <tr>
                <td>Total:</td>
                <td>$${total}.00</td>
            </tr>
        </table>
    </div>
    <tr>
       <a id="btnClean" onclick="clean()" class="btnd">Borrar orden</a>
       <a id="btnBuy" onclick="buy()" class="btn">Comprar </a>
    </tr>
    `;
    products=JSON.parse(localStorage.getItem("cart"));
    cart_n.innerHTML=`[${products.length}]`;
}