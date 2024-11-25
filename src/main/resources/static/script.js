// Variáveis Globais
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Funções de Manipulação de Produtos
function buscarProdutos() {
    const query = document.querySelector('.Buscar-bar input').value;
    alert('Buscando por: ' + query);
}

function addToCart(productName, productPrice) {
  const existingItemIndex = cart.findIndex(item => item.name === productName);

  if (existingItemIndex !== -1) {
      // Item já existe, aumenta a quantidade
      cart[existingItemIndex].quantity += 1;
  } else {
      // Item não existe, adiciona ao carrinho com quantidade 1
      cart.push({ name: productName, price: productPrice, quantity: 1 });
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  alert(productName + ' foi adicionado ao seu carrinho!');
  window.location.href = 'carrinho.html';
}

// Funções de Manipulação do Carrinho
function displayCartItems() {
  const cartItems = JSON.parse(localStorage.getItem('cart')) || [];
  const cartList = document.getElementById('cartItems');
  const totalPriceElement = document.getElementById('totalPrice');
  const paymentButton = document.getElementById('paymentButton');

    cartList.innerHTML = '';
  let total = 0;

  if (cartItems.length === 0) {
      cartList.innerHTML = "<li>Seu carrinho está vazio.</li>";
      totalPriceElement.textContent = '0.00';
      localStorage.setItem('totalPrice', '0.00'); // Armazena o total no localStorage
        //desabilita o click do botao
        paymentButton.setAttribute('onClick', 'return false');
        paymentButton.style.backgroundColor = '#cccccc';

  } else {
      cartItems.forEach((item, index) => {
          const li = document.createElement('li');
          li.className = 'cart-item';
          li.textContent = `${item.name} - R$${item.price.toFixed(2)} (Quantidade: ${item.quantity})`;
          total += item.price * item.quantity; // Multiplica o preço pela quantidade

          const removeButton = document.createElement('button');
          removeButton.textContent = 'Remover';
          removeButton.className = 'remove-button';
          removeButton.onclick = () => removeFromCart(index);

          li.appendChild(removeButton);
          cartList.appendChild(li);
      });
      totalPriceElement.textContent = total.toFixed(2);
      localStorage.setItem('totalPrice', total.toFixed(2));
  }
}

function removeFromCart(index) {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  
  // Remove um item do carrinho
  if (cart[index].quantity > 1) {
      // Se a quantidade for maior que 1, diminui a quantidade
      cart[index].quantity -= 1;
  } else {
      // Se a quantidade for 1, remove o item
      cart.splice(index, 1);
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  displayCartItems();
}

// Função para Finalizar a Compra
async function finalizarCompra() {
    const totalPrice = (localStorage.getItem('totalPrice'));
    const idCliente = "ASJHDBA-897IKJHBK-AHSBD787"; // MUDAR APÒS PEDRO
    const paymentMethod = document.getElementById('payment-method').value;

    const dadosPagamento = {
        valor: totalPrice,
        idCliente: idCliente,
        tipo: paymentMethod,
        produtos: JSON.parse(localStorage.getItem('cart'))
    };

    const jsonDadosPagamento = JSON.stringify(dadosPagamento);
    console.log(jsonDadosPagamento);
    
    const url = "/v1/transacao/new";

    try {
        const resultado = await fetch(url,{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(dadosPagamento)
        });

        if (resultado.status == 200) {
          alert("Pagamento finalizado!");
            localStorage.removeItem('totalPrice');
            localStorage.removeItem('cart');
            window.location.href = 'index.html';
        } else {
            console.log("Produto não encontrado");
        }
    } catch (error) {
        console.error("Erro ao buscar dados:", error);
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const path = window.location.pathname;

    if (path.includes('index.html')) {
        // Código específico para index.html
    } else if (path.includes('carrinho.html')) {
        displayCartItems();
    } else if (path.includes('pilha.html')) {
        const btn = document.getElementById("btn_enviar");
        const txtInput = document.getElementById("txt_input");
        const txtOutput = document.getElementById("txt_output");

        if (btn) {
            btn.addEventListener("click", getData);
        }
    } else if (path.includes('pagamento.html')) {
        const totalPriceDisplay = document.getElementById('totalPriceDisplay');
        const totalPrice = localStorage.getItem('totalPrice') || '0.00'; // Recupera o total do local Storage
        totalPriceDisplay.textContent = `R$${totalPrice}`; // Atualiza o conteúdo do elemento
    }
});