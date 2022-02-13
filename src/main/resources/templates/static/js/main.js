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
  var products=[];
  var cartItems=[];
  var cart_n=document.getElementById('cart_n');

  var cliente=document.getElementById('user-n');

  var piezasDIV=document.getElementById("piezasDIV");
  var ps4DIV=document.getElementById("ps4DIV");
  var joyeriaDIV=document.getElementById("joyeriaDIV");



  var PS4=[
        {nombre : 'IT ',precio:7},
        {nombre : 'EL RENACIDO',precio:20},
        {nombre : 'LA BELLA Y LA BESTIA',precio:6,},
        {nombre : 'BTMAN',precio:25}

      ];
   var PIEZAS=[

       { nombre : 'SPIDERMAN',precio:7},
       { nombre : ' Superman ',precio:5}

      ];
  var JOYERIA=[

      {nombre : 'DURO DE MATAR',precio:4},
      {nombre : 'IT 2',precio:4}

    ];

function HTMLpiezasProducto(con){
        let URL=`img/piezas/fruta${con}.jpeg`;
        let btn= `btnPiezas${con}`;
        return `

        <div class="col-4">
            <img src="${URL}">
            <h3>${PIEZAS[con-1].nombre}</h3>
            <h4>Disponible: Blanco y Verde</h4>
                <div class="rating">
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star-o" ></i>
                </div>
             <p> $${PIEZAS[con-1].precio}.00</p>

                <div class="col">
                    <div >
                        <a class="btnd" href="cart.html">Checkout</a>
                        <a class="btn" id="${btn}"  onclick="cart('${PIEZAS[con-1].nombre}','${PIEZAS[con-1].precio}','${URL}','${con}','${btn}')">Add Ticket</a>

                </div>
                </div>
        </div>
        `
    }


    function HTMLps4Producto(con){
        let URL=`img/ps4/ps4${con}.jpeg`;
        let btn= `btnPs4${con}`;
        return `

        <div class="col-4">
        <img src="${URL}">
               <h3>${PS4[con-1].nombre}</h3>
               <h4>Disponible: Blanco y Verde</h4>
                <div class="rating">
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star-o" ></i>
                </div>

             <p> $${PS4[con-1].precio}.00</p>
                <div class="col">
                    <div >
                        <a class="btnd" href="cart.html">Checkout</a>
                        <a class="btn" id="${btn}"  onclick="cart('${PS4[con-1].nombre}','${PS4[con-1].precio}','${URL}','${con}','${btn}')">Add Ticket</a>

                </div>
                </div>
        </div>
        `
    }

    function HTMLjoyeriaProducto(con){
        let URL=`img/joyeria/joye${con}.jpeg`;
        let btn= `btnJoyeria${con}`;
        return `

        <div class="col-4">
        <img src="${URL}">
            <h3>${JOYERIA[con-1].nombre}</h3>
            <h4>Disponible: Blanco y Verde</h4>
                <div class="rating">
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star" ></i>
                    <i class="fa fa-star-o" ></i>
                </div>
             <p> $${JOYERIA[con-1].precio}.00</p>
                <div class="col">
                    <div >
                        <a class="btnd" href="cart.html">Checkout</a>
                        <a class="btn" id="${btn}"  onclick="cart('${JOYERIA[con-1].nombre}','${JOYERIA[con-1].precio}','${URL}','${con}','${btn}')">Add Ticket</a>
                </div>
                </div>
        </div>
        `
    }

function animation(){
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: false,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
      Toast.fire({
        icon: 'success',
        title: 'Agregado al carrito'
      });
}

function cart(nombre,precio,url,con,btncart){
    var item={
        Nombre:nombre,
        Precio:precio,
        Url:url
    }

    cartItems.push(item);
    let storage=JSON.parse(localStorage.getItem("cart"));
    if(storage==null){
        products.push(item);
        localStorage.setItem("cart",JSON.stringify(products));

    } else{
        products=JSON.parse(localStorage.getItem("cart"));
        products.push(item);
        localStorage.setItem("cart",JSON.stringify(products));
    }
    products=JSON.parse(localStorage.getItem("cart"));
    animation();
    cart_n.innerHTML=`[${products.length}]`;
    document.getElementById(btncart);

}

function render(){
    for(let index=1; index <= PIEZAS.length; index++){
        piezasDIV.innerHTML+=`${HTMLpiezasProducto(index)}`;
    }
    for(let index=1; index <= PS4.length; index++){
        ps4DIV.innerHTML+=`${HTMLps4Producto(index)}`;
    }
    for(let index=1; index <= JOYERIA.length; index++){
        joyeriaDIV.innerHTML+=`${HTMLjoyeriaProducto(index)}`;
    }

    if (localStorage.getItem("cart")==null) {

    } else {
        products=JSON.parse(localStorage.getItem("cart"));
        cart_n.innerHTML=`[${products.length}]`;
    }
}

