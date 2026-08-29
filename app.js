// Marmitaria do Dia - Application Logic

// ==========================================================================
// CARDÁPIO DATABASE (WEEKLY MENU DATA)
// ==========================================================================
const MENU_DATA = {
    "Segunda-feira": {
        folder: "02 - Segunda",
        price: 27.00,
        options: [
            { id: "mon_1", num: "1ª Opção", name: "Strogonoff de Carne", accompaniments: "Arroz e batata palha", image: "Cardápio/02 - Segunda/01.jpeg" },
            { id: "mon_2", num: "2ª Opção", name: "Berinjela à Parmegiana", accompaniments: "Arroz e batata cozida", image: "Cardápio/02 - Segunda/01.jpeg" },
            { id: "mon_3", num: "3ª Opção", name: "Bisteca Grelhada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/02 - Segunda/01.jpeg" },
            { id: "mon_4", num: "4ª Opção", name: "Filé de Frango Grelhado", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/02 - Segunda/01.jpeg" },
            { id: "mon_5", num: "5ª Opção", name: "Linguiça Acebolada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/02 - Segunda/02.jpeg" },
            { id: "mon_6", num: "6ª Opção", name: "Omelete de Presunto e Queijo", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/02 - Segunda/02.jpeg" }
        ]
    },
    "Terça-feira": {
        folder: "03 - Terça",
        price: 27.00,
        options: [
            { id: "tue_1", num: "1ª Opção", name: "Carne de Panela", accompaniments: "Arroz, feijão e purê", image: "Cardápio/03 - Terça/01.jpeg" },
            { id: "tue_2", num: "2ª Opção", name: "Pernil Assado", accompaniments: "Arroz, feijão, farofa e vinagrete", image: "Cardápio/03 - Terça/01.jpeg" },
            { id: "tue_3", num: "3ª Opção", name: "Strogonoff de Frango", accompaniments: "Arroz e batata palha", image: "Cardápio/03 - Terça/01.jpeg" },
            { id: "tue_4", num: "4ª Opção", name: "Filé de Frango Grelhado", accompaniments: "Arroz, feijão e purê", image: "Cardápio/03 - Terça/01.jpeg" },
            { id: "tue_5", num: "5ª Opção", name: "Linguiça Acebolada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/03 - Terça/02.jpeg" },
            { id: "tue_6", num: "6ª Opção", name: "Omelete de Presunto e Queijo", accompaniments: "Arroz, feijão e purê", image: "Cardápio/03 - Terça/02.jpeg" }
        ]
    },
    "Quarta-feira": {
        folder: "04 - Quarta",
        price: 27.00, // standard price for others
        options: [
            { id: "wed_1", num: "1ª Opção", name: "Feijoada Light", accompaniments: "Arroz, couve, farofa e molho de pimenta", price: 40.00, image: "Cardápio/04 - Quarta/01.jpeg" },
            { id: "wed_2", num: "2ª Opção", name: "Coxa e Sobrecoxa Assada", accompaniments: "Arroz, feijão e macarrão", price: 27.00, image: "Cardápio/04 - Quarta/01.jpeg" },
            { id: "wed_3", num: "3ª Opção", name: "Bife a Cavalo", accompaniments: "Arroz, feijão e farofa", price: 27.00, image: "Cardápio/04 - Quarta/01.jpeg" },
            { id: "wed_4", num: "4ª Opção", name: "Filé de Frango Grelhado", accompaniments: "Arroz, feijão e legumes", price: 27.00, image: "Cardápio/04 - Quarta/01.jpeg" },
            { id: "tue_5", num: "5ª Opção", name: "Linguiça Acebolada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/03 - Terça/02.jpeg" },
            { id: "tue_6", num: "6ª Opção", name: "Omelete de Presunto e Queijo", accompaniments: "Arroz, feijão e purê", image: "Cardápio/03 - Terça/02.jpeg" }
        ]
    },
    "Quinta-feira": {
        folder: "05 - Quinta",
        price: 27.00,
        options: [
            { id: "thu_1", num: "1ª Opção", name: "Filé de Frango à Parmegiana", accompaniments: "Arroz, feijão e batata cozida", image: "Cardápio/05 - Quinta/01.jpeg" },
            { id: "thu_2", num: "2ª Opção", name: "Panqueca de Carne", accompaniments: "Arroz à grega e batata cozida", image: "Cardápio/05 - Quinta/01.jpeg" },
            { id: "thu_3", num: "3ª Opção", name: "Filé de Frango à Milanesa", accompaniments: "Arroz, feijão e batata cozida", image: "Cardápio/05 - Quinta/01.jpeg" },
            { id: "thu_4", num: "4ª Opção", name: "Filé de Frango Grelhado", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/05 - Quinta/01.jpeg" },
            { id: "thu_5", num: "5ª Opção", name: "Linguiça Acebolada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/05 - Quinta/02.jpeg" },
            { id: "thu_6", num: "6ª Opção", name: "Omelete de Presunto e Queijo", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/05 - Quinta/02.jpeg" }
        ]
    },
    "Sexta-feira": {
        folder: "06 - Sexta",
        price: 27.00,
        options: [
            { id: "fri_1", num: "1ª Opção", name: "Bife à Parmegiana", accompaniments: "Arroz, feijão e purê", image: "Cardápio/06 - Sexta/01.jpeg" },
            { id: "fri_2", num: "2ª Opção", name: "Picadinho de Carne", accompaniments: "Batata, cenoura, arroz, feijão e farofa", image: "Cardápio/06 - Sexta/01.jpeg" },
            { id: "fri_3", num: "3ª Opção", name: "Posta de Cação ao Molho", accompaniments: "Arroz, feijão e purê", image: "Cardápio/06 - Sexta/01.jpeg" },
            { id: "fri_4", num: "4ª Opção", name: "Filé de Frango Grelhado", accompaniments: "Arroz, feijão e purê", image: "Cardápio/06 - Sexta/01.jpeg" },
            { id: "fri_5", num: "5ª Opção", name: "Linguiça Acebolada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/06 - Sexta/02.jpeg" },
            { id: "fri_6", num: "6ª Opção", name: "Omelete de Presunto e Queijo", accompaniments: "Arroz, feijão e purê", image: "Cardápio/06 - Sexta/02.jpeg" }
        ]
    },
    "Sábado": {
        folder: "07 - Sábado",
        price: 27.00,
        options: [
            { id: "sat_1", num: "1ª Opção", name: "Feijoada Light", accompaniments: "Arroz, couve e molho de pimenta", price: 40.00, image: "Cardápio/07 - Sábado/01.jpeg" },
            { id: "sat_2", num: "2ª Opção", name: "Strogonoff de Frango", accompaniments: "Arroz, feijão e batata palha", image: "Cardápio/07 - Sábado/01.jpeg" },
            { id: "sat_3", num: "3ª Opção", name: "Bife Grelhado", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/07 - Sábado/01.jpeg" },
            { id: "sat_4", num: "4ª Opção", name: "Filé de Frango Grelhado", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/07 - Sábado/01.jpeg" },
            { id: "sat_5", num: "5ª Opção", name: "Linguiça Acebolada", accompaniments: "Arroz, feijão e farofa", image: "Cardápio/07 - Sábado/02.jpeg" },
            { id: "sat_6", num: "6ª Opção", name: "Omelete de Presunto e Queijo", accompaniments: "Arroz, feijão e legumes", image: "Cardápio/07 - Sábado/02.jpeg" }
        ]
    }
};

const DRINKS = [
    { id: "drink_1", name: "Refrigerante Lata 350ml", desc: "Coca-Cola, Guaraná ou Fanta", price: 6.00 },
    { id: "drink_2", name: "Mini Refrigerante 200ml", desc: "Sabor Coca-Cola ou Guaraná", price: 3.00 }
];

const WEEKDAYS = ["Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"];
const WHATSAPP_PHONE = "5511970599173"; // Cleaned standard phone as requested

// ==========================================================================
// STATE MANAGEMENT
// ==========================================================================
let currentDaySelected = "";
let cart = [];
let tempItemCustomize = null; // Stores item being configured in modal
let activeScreen = "menu-screen";
let trackingInterval = null;
let simulatedTrackingStep = 0;

// LocalStorage helpers to retain state
function saveCartToStorage() {
    localStorage.setItem("marmita_cart", JSON.stringify(cart));
    updateCartBadge();
}

function loadCartFromStorage() {
    const saved = localStorage.getItem("marmita_cart");
    if (saved) {
        try {
            cart = JSON.parse(saved);
        } catch (e) {
            cart = [];
        }
    }
    updateCartBadge();
}

// ==========================================================================
// INITIALIZATION
// ==========================================================================
document.addEventListener("DOMContentLoaded", () => {
    loadCartFromStorage();
    detectAndSetCurrentDay();
    renderDaySelector();
    renderMenu();
    renderDrinks();
    setupEventListeners();

    // Smooth check out field toggle logic
    toggleDeliveryFields("delivery");
});

// Detect actual day of week and pre-select
function detectAndSetCurrentDay() {
    const dayIndex = new Date().getDay();
    const dayName = WEEKDAYS[dayIndex];

    if (dayIndex === 0) { // Sunday - closed
        currentDaySelected = "Segunda-feira"; // Default display
        showSundayWarning();
    } else {
        currentDaySelected = dayName;
    }
}

function showSundayWarning() {
    const warning = document.getElementById("closed-warning");
    if (warning) {
        warning.style.display = "block";
    }
}

// ==========================================================================
// RENDERERS
// ==========================================================================
function renderDaySelector() {
    const carousel = document.getElementById("day-selector-scroll");
    if (!carousel) return;
    carousel.innerHTML = "";

    const todayIndex = new Date().getDay();
    const todayName = WEEKDAYS[todayIndex];

    WEEKDAYS.forEach((day, index) => {
        if (index === 0) return; // Skip Sunday in selector

        const isToday = day === todayName;
        const isActive = day === currentDaySelected;

        const btn = document.createElement("button");
        btn.className = `day-btn ${isActive ? 'active' : ''} ${isToday ? 'today' : ''}`;
        btn.onclick = () => selectDay(day);

        const dayShort = day.split("-")[0];

        btn.innerHTML = `
            <span class="day-name">${dayShort}</span>
            <span class="day-status">${isToday ? 'Hoje' : 'Cardápio'}</span>
        `;

        carousel.appendChild(btn);

        // Center selected button inside the horizontal carousel
        if (isActive) {
            setTimeout(() => {
                btn.scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'center' });
            }, 80);
        }
    });
}

function selectDay(day) {
    currentDaySelected = day;
    renderDaySelector();
    renderMenu();

    // Close Sunday warning if user browses days
    const warning = document.getElementById("closed-warning");
    if (warning) warning.style.display = "none";

    showToast(`Mostrando cardápio de ${day}`);
}

function renderMenu() {
    const menuGrid = document.getElementById("menu-grid");
    if (!menuGrid) return;
    menuGrid.innerHTML = "";

    const dayData = MENU_DATA[currentDaySelected];
    if (!dayData) return;

    document.getElementById("selected-day-title").textContent = currentDaySelected;

    dayData.options.forEach((opt) => {
        const itemPrice = opt.price || dayData.price;

        const card = document.createElement("div");
        card.className = "food-card";
        card.onclick = () => openCustomizeModal(opt, itemPrice);

        card.innerHTML = `
            <div class="food-header">
                <div>
                    <span class="food-option-num">${opt.num}</span>
                    <h3 class="food-title">${opt.name}</h3>
                </div>
                <span class="food-price">R$ ${itemPrice.toFixed(2).replace('.', ',')}</span>
            </div>
            <p class="food-description">${opt.accompaniments}.</p>
            <div class="food-footer">
                <span class="badge-tag">Acomp. Salada</span>
                <button class="add-btn">
                    <svg viewBox="0 0 24 24"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
                    Pedir
                </button>
            </div>
        `;

        menuGrid.appendChild(card);
    });
}

function renderDrinks() {
    const drinksGrid = document.getElementById("drinks-grid");
    if (!drinksGrid) return;
    drinksGrid.innerHTML = "";

    DRINKS.forEach(drink => {
        const card = document.createElement("div");
        card.className = "drink-card";
        card.onclick = () => addDrinkDirectly(drink);

        card.innerHTML = `
            <h4 class="drink-title">${drink.name}</h4>
            <p class="food-description" style="margin-bottom:4px; font-size:11px;">${drink.desc}</p>
            <div class="drink-price-row">
                <span class="drink-price">R$ ${drink.price.toFixed(2).replace('.', ',')}</span>
                <span class="drink-add">+</span>
            </div>
        `;
        drinksGrid.appendChild(card);
    });
}

// ==========================================================================
// ITEM CUSTOMIZE MODAL
// ==========================================================================
function openCustomizeModal(mealOption, price) {
    tempItemCustomize = {
        id: mealOption.id,
        name: mealOption.name,
        num: mealOption.num,
        basePrice: price,
        qty: 1,
        preferences: "",
        adicionais: []
    };

    document.getElementById("cust-title").textContent = mealOption.name;
    document.getElementById("cust-num").textContent = mealOption.num;
    document.getElementById("cust-accompaniments").textContent = `${mealOption.accompaniments}. Todas as marmitas acompanham salada.`;
    document.getElementById("cust-qty").textContent = "1";

    // Clear dynamic UI controls
    document.getElementById("cust-pref-text").value = "";

    // Reset checkboxed additional toppings
    document.querySelectorAll(".addon-checkbox").forEach(chk => {
        chk.checked = false;
    });

    updateCustomizePrice();
    openOverlay("cust-sheet-overlay");
}

function updateCustomizePrice() {
    if (!tempItemCustomize) return;

    let price = tempItemCustomize.basePrice;

    // Add toppings
    tempItemCustomize.adicionais.forEach(add => {
        price += add.price;
    });

    const totalPrice = price * tempItemCustomize.qty;
    document.getElementById("cust-total-price").textContent = `R$ ${totalPrice.toFixed(2).replace('.', ',')}`;
}

function adjustQty(amount) {
    if (!tempItemCustomize) return;
    const newQty = tempItemCustomize.qty + amount;
    if (newQty >= 1) {
        tempItemCustomize.qty = newQty;
        document.getElementById("cust-qty").textContent = newQty;
        updateCustomizePrice();
    }
}

function toggleAddon(checkboxElement, name, price) {
    if (!tempItemCustomize) return;

    if (checkboxElement.checked) {
        tempItemCustomize.adicionais.push({ name, price });
    } else {
        tempItemCustomize.adicionais = tempItemCustomize.adicionais.filter(a => a.name !== name);
    }
    updateCustomizePrice();
}

function confirmCustomize() {
    if (!tempItemCustomize) return;

    // Get text preferences
    tempItemCustomize.preferences = document.getElementById("cust-pref-text").value.trim();

    // Construct single cart item
    const cartItem = {
        cartId: Date.now() + "_" + Math.random().toString(36).substr(2, 9),
        id: tempItemCustomize.id,
        name: tempItemCustomize.name,
        num: tempItemCustomize.num,
        qty: tempItemCustomize.qty,
        preferences: tempItemCustomize.preferences,
        adicionais: [...tempItemCustomize.adicionais],
        unitPrice: tempItemCustomize.basePrice + tempItemCustomize.adicionais.reduce((sum, current) => sum + current.price, 0)
    };

    cart.push(cartItem);
    saveCartToStorage();

    closeOverlay("cust-sheet-overlay");
    showToast(`Adicionado: ${cartItem.qty}x ${cartItem.name}`);
    animateCartIcon();
}

function addDrinkDirectly(drink) {
    const cartItem = {
        cartId: Date.now() + "_" + Math.random().toString(36).substr(2, 9),
        id: drink.id,
        name: drink.name,
        qty: 1,
        preferences: "",
        adicionais: [],
        unitPrice: drink.price
    };

    cart.push(cartItem);
    saveCartToStorage();
    showToast(`Adicionado: ${drink.name}`);
    animateCartIcon();
}

// ==========================================================================
// CART OPERATIONS & RENDERING
// ==========================================================================
function renderCartDrawer() {
    const cartList = document.getElementById("cart-items-list");
    const emptyState = document.getElementById("cart-empty-state");
    const footer = document.getElementById("cart-drawer-footer");

    if (!cartList) return;
    cartList.innerHTML = "";

    if (cart.length === 0) {
        emptyState.style.display = "flex";
        footer.style.display = "none";
        return;
    }

    emptyState.style.display = "none";
    footer.style.display = "flex";

    let subtotal = 0;

    cart.forEach(item => {
        const itemTotal = item.unitPrice * item.qty;
        subtotal += itemTotal;

        const card = document.createElement("div");
        card.className = "cart-item";

        // Calculate base price of the item alone (subtracting any additions)
        const addonsTotal = item.adicionais.reduce((sum, curr) => sum + curr.price, 0);
        const basePrice = item.unitPrice - addonsTotal;

        let detailsHtml = "";
        if (item.adicionais.length > 0) {
            detailsHtml += `<span style="opacity: 0.65; font-size: 10px;">Valor base: R$ ${basePrice.toFixed(2).replace('.', ',')}</span><br>`;
            detailsHtml += `<div style="margin: 3px 0; color: var(--text-gold); display: flex; flex-direction: column; gap: 2px;">`;
            item.adicionais.forEach(a => {
                detailsHtml += `<span style="font-size: 11px; font-weight: 500;">+ ${a.name} (+ R$ ${a.price.toFixed(2).replace('.', ',')})</span>`;
            });
            detailsHtml += `</div>`;
        }
        if (item.preferences) {
            detailsHtml += `<span style="display:block; margin-top:4px; font-style:italic;">Obs: "${item.preferences}"</span>`;
        }

        card.innerHTML = `
            <div class="cart-item-info">
                <h4 class="cart-item-title">${item.qty}x ${item.name}</h4>
                ${detailsHtml ? `<div class="cart-item-details">${detailsHtml}</div>` : ""}
                <div class="cart-item-price">R$ ${itemTotal.toFixed(2).replace('.', ',')}</div>
            </div>
            <div class="cart-item-actions">
                <button class="item-delete-btn" onclick="removeFromCart('${item.cartId}')">
                    <svg style="width:18px;height:18px" viewBox="0 0 24 24"><path fill="currentColor" d="M9,3V4H4V6H20V4H15V3H9M5,8V19A2,2 0 0,0 7,21H17A2,2 0 0,0 19,19V8H5M9,10H11V17H9V10M13,10H15V17H13V10Z"/></svg>
                </button>
                <div class="cart-qty-control">
                    <button class="cart-qty-btn" onclick="adjustCartItemQty('${item.cartId}', -1)">-</button>
                    <div class="cart-qty-val">${item.qty}</div>
                    <button class="cart-qty-btn" onclick="adjustCartItemQty('${item.cartId}', 1)">+</button>
                </div>
            </div>
        `;
        cartList.appendChild(card);
    });

    // Subtotal & Total matching (Delivery is free R$ 0,00)
    document.getElementById("cart-subtotal").textContent = `R$ ${subtotal.toFixed(2).replace('.', ',')}`;
    document.getElementById("cart-total").textContent = `R$ ${subtotal.toFixed(2).replace('.', ',')}`;
}

function adjustCartItemQty(cartId, change) {
    const item = cart.find(i => i.cartId === cartId);
    if (!item) return;

    const newQty = item.qty + change;
    if (newQty >= 1) {
        item.qty = newQty;
    } else {
        cart = cart.filter(i => i.cartId !== cartId);
    }
    saveCartToStorage();
    renderCartDrawer();
}

function removeFromCart(cartId) {
    cart = cart.filter(i => i.cartId !== cartId);
    saveCartToStorage();
    renderCartDrawer();
    showToast("Item removido do carrinho");
}

function getCartTotal() {
    return cart.reduce((sum, item) => sum + (item.unitPrice * item.qty), 0);
}

function updateCartBadge() {
    const count = cart.reduce((sum, item) => sum + item.qty, 0);
    const badges = document.querySelectorAll(".cart-badge");
    badges.forEach(b => {
        if (count > 0) {
            b.textContent = count;
            b.style.display = "flex";
        } else {
            b.style.display = "none";
        }
    });
}

// ==========================================================================
// CHECKOUT & PAYMENT LÓGICA
// ==========================================================================
function toggleDeliveryFields(type) {
    const addressFields = document.getElementById("address-fields-container");
    const takeoutNotice = document.getElementById("takeout-notice");
    const toggleDelivery = document.getElementById("toggle-delivery");
    const toggleTakeout = document.getElementById("toggle-takeout");

    if (type === "delivery") {
        addressFields.style.display = "block";
        takeoutNotice.style.display = "none";
        toggleDelivery.classList.add("active");
        toggleTakeout.classList.remove("active");

        // Form validations
        document.querySelectorAll("#address-fields-container .form-control").forEach(inp => {
            if (inp.id !== "chk-complement" && inp.id !== "chk-ref") {
                inp.setAttribute("required", "true");
            }
        });
    } else {
        addressFields.style.display = "none";
        takeoutNotice.style.display = "block";
        toggleDelivery.classList.remove("active");
        toggleTakeout.classList.add("active");

        // Remove validations
        document.querySelectorAll("#address-fields-container .form-control").forEach(inp => {
            inp.removeAttribute("required");
        });
    }
}

function selectPaymentMethod(method) {
    const methods = ["pix", "card", "delivery-cash", "delivery-card"];
    methods.forEach(m => {
        const opt = document.getElementById(`pay-opt-${m}`);
        if (opt) opt.classList.remove("active");
    });

    const selectedOpt = document.getElementById(`pay-opt-${method}`);
    if (selectedOpt) selectedOpt.classList.add("active");

    // Extra options containers
    document.getElementById("credit-card-container").style.display = method === "card" ? "block" : "none";
    document.getElementById("cash-change-container").style.display = method === "delivery-cash" ? "block" : "none";
}

function updateChangeRequired(required) {
    const changeInput = document.getElementById("cash-change-input-group");
    if (required) {
        changeInput.style.display = "block";
        document.getElementById("chk-change-value").setAttribute("required", "true");
    } else {
        changeInput.style.display = "none";
        document.getElementById("chk-change-value").removeAttribute("required");
    }
}

function proceedToCheckout() {
    if (cart.length === 0) {
        showToast("Adicione itens ao carrinho primeiro!");
        return;
    }

    closeOverlay("cart-drawer-overlay");

    // Set total prices in checkout
    const total = getCartTotal();
    document.getElementById("chk-subtotal-val").textContent = `R$ ${total.toFixed(2).replace('.', ',')}`;
    document.getElementById("chk-total-val").textContent = `R$ ${total.toFixed(2).replace('.', ',')}`;

    openOverlay("checkout-drawer-overlay");
}

function processOrderSubmit(event) {
    event.preventDefault();

    const isDelivery = document.getElementById("toggle-delivery").classList.contains("active");

    // Basic Form validation
    const name = document.getElementById("chk-name").value.trim();
    const phone = document.getElementById("chk-phone").value.trim();

    if (!name || !phone) {
        showToast("Preencha Nome e Telefone!");
        return;
    }

    if (isDelivery) {
        const street = document.getElementById("chk-street").value.trim();
        const num = document.getElementById("chk-num").value.trim();
        const neighborhood = document.getElementById("chk-neighborhood").value.trim();

        if (!street || !num || !neighborhood) {
            showToast("Preencha os dados do endereço de entrega!");
            return;
        }
    }

    // Identify Payment Method
    let payMethod = "pix";
    if (document.getElementById("pay-opt-card").classList.contains("active")) payMethod = "card";
    if (document.getElementById("pay-opt-delivery-cash").classList.contains("active")) payMethod = "delivery-cash";
    if (document.getElementById("pay-opt-delivery-card").classList.contains("active")) payMethod = "delivery-card";

    // Double validation for Card Details if Credit Card chosen online
    if (payMethod === "card") {
        const cardNum = document.getElementById("card-number-input").value.replace(/\s+/g, '');
        const cardOwner = document.getElementById("card-owner-input").value.trim();
        if (cardNum.length < 16 || !cardOwner) {
            showToast("Preencha os dados do cartão de crédito corretamente!");
            return;
        }
    }

    // Validation for Cash change
    let changeText = "";
    if (payMethod === "delivery-cash") {
        const needChange = document.getElementById("change-yes").checked;
        if (needChange) {
            const changeVal = parseFloat(document.getElementById("chk-change-value").value);
            const total = getCartTotal();
            if (isNaN(changeVal) || changeVal <= total) {
                showToast(`O valor para troco deve ser maior que o total R$ ${total.toFixed(2).replace('.', ',')}`);
                return;
            }
            changeText = `Troco para R$ ${changeVal.toFixed(2).replace('.', ',')}`;
        } else {
            changeText = "Sem necessidade de troco";
        }
    }

    // If Pix Selected -> Show Pix modal
    if (payMethod === "pix") {
        closeOverlay("checkout-drawer-overlay");
        openOverlay("pix-modal-overlay");
        return;
    }

    // For credit card / delivery payments directly finalize
    finalizeOrder(payMethod, changeText);
}

function finalizeOrder(payMethod, changeText = "") {
    // Stop previous tracking if any
    if (trackingInterval) clearInterval(trackingInterval);

    closeOverlay("checkout-drawer-overlay");
    closeOverlay("pix-modal-overlay");

    // Switch screen to order tracking
    switchScreen("order-screen");

    // Launch celebratory confetti
    triggerConfetti();

    // Setup Tracking Timeline
    setupTrackingTimeline(payMethod, changeText);
}

function setupTrackingTimeline(payMethod, changeText = "") {
    const steps = document.querySelectorAll("#order-timeline .timeline-step");
    steps.forEach(s => {
        s.className = "timeline-step";
        const timeBadge = s.querySelector(".timeline-time");
        if (timeBadge) timeBadge.textContent = "--:--";
    });

    simulatedTrackingStep = 0;
    updateTrackingStepUI(0);

    // Start tracking ticks
    trackingInterval = setInterval(() => {
        simulatedTrackingStep++;
        if (simulatedTrackingStep <= 3) {
            updateTrackingStepUI(simulatedTrackingStep);
        } else {
            clearInterval(trackingInterval);
        }
    }, 15000); // Progresses every 15s for the demo tracking
}

function updateTrackingStepUI(stepIndex) {
    const steps = document.querySelectorAll("#order-timeline .timeline-step");
    const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

    for (let i = 0; i <= stepIndex; i++) {
        if (i < stepIndex) {
            steps[i].className = "timeline-step completed";
        } else {
            steps[i].className = "timeline-step active";
            const timeBadge = steps[i].querySelector(".timeline-time");
            if (timeBadge) timeBadge.textContent = currentTime;
        }
    }

    // Update tracking title info
    const titles = [
        "Pedido Recebido pela Marmitaria!",
        "Seu marmitex está sendo preparado na cozinha!",
        "Saiu para Entrega / Pronto para Retirada!",
        "Pedido entregue! Bom apetite!"
    ];
    document.getElementById("tracking-status-text").textContent = titles[stepIndex];
}

// ==========================================================================
// WHATSAPP INTEGRATION & MESSAGE FORMATTING
// ==========================================================================
function sendWhatsAppOrder() {
    const name = document.getElementById("chk-name").value.trim();
    const phone = document.getElementById("chk-phone").value.trim();
    const isDelivery = document.getElementById("toggle-delivery").classList.contains("active");

    let payMethod = "PIX";
    if (document.getElementById("pay-opt-card").classList.contains("active")) payMethod = "Cartão de Crédito Online";
    if (document.getElementById("pay-opt-delivery-cash").classList.contains("active")) payMethod = "Dinheiro (Pagar na Entrega)";
    if (document.getElementById("pay-opt-delivery-card").classList.contains("active")) payMethod = "Cartão na Maquininha (Pagar na Entrega)";

    let changeInfo = "";
    if (payMethod.includes("Dinheiro")) {
        const needChange = document.getElementById("change-yes").checked;
        if (needChange) {
            const changeVal = parseFloat(document.getElementById("chk-change-value").value);
            changeInfo = ` (Troco para R$ ${changeVal.toFixed(2).replace('.', ',')})`;
        } else {
            changeInfo = " (Sem troco)";
        }
    }

    let addressInfo = "";
    if (isDelivery) {
        const street = document.getElementById("chk-street").value.trim();
        const num = document.getElementById("chk-num").value.trim();
        const comp = document.getElementById("chk-complement").value.trim();
        const neighborhood = document.getElementById("chk-neighborhood").value.trim();
        const ref = document.getElementById("chk-ref").value.trim();

        addressInfo = `*Rua:* ${street}, *Nº:* ${num}\n` +
            (comp ? `*Apto/Bloco:* ${comp}\n` : "") +
            `*Bairro:* ${neighborhood}` +
            (ref ? `\n*Referência:* ${ref}` : "");
    } else {
        addressInfo = "_Cliente fará a Retirada no Balcão (Sem Taxa)_";
    }

    // Items Formatting
    let itemsText = "";
    cart.forEach(item => {
        let details = [];
        if (item.adicionais.length > 0) {
            details.push(`Adicionais: ${item.adicionais.map(a => `${a.name} (+ R$ ${a.price.toFixed(2).replace('.', ',')})`).join(", ")}`);
        }
        if (item.preferences) {
            details.push(`Obs: "${item.preferences}"`);
        }

        itemsText += `• *${item.qty}x ${item.name}* (R$ ${item.unitPrice.toFixed(2).replace('.', ',')})\n`;
        if (details.length > 0) {
            itemsText += `   _${details.join(" | ")}_\n`;
        }
    });

    const total = getCartTotal();

    // Build professional Brazilian portuguese message
    const message =
        `*🍔 NOVO PEDIDO - MARMITARIA DO DIA*
----------------------------------
*CLIENTE:* ${name}
*TELEFONE:* ${phone}

*ITENS DO PEDIDO:*
${itemsText}
*FORMA DE ENVIO:* ${isDelivery ? "🚚 Delivery / Entrega" : "🏪 Retirada no Balcão"}
${isDelivery ? `\n*ENDEREÇO DE ENTREGA:*\n${addressInfo}` : `\n*PONTO DE RETIRADA:*\n${addressInfo}`}

*FORMA DE PAGAMENTO:* ${payMethod}${changeInfo}

*TOTAL DO PEDIDO:* *R$ ${total.toFixed(2).replace('.', ',')}*
----------------------------------
Obrigado pela preferência! 😊`;

    const encodedText = encodeURIComponent(message);
    const waUrl = `https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE}&text=${encodedText}`;
    window.open(waUrl, "_blank");
}

// ==========================================================================
// UTILITY ANIMATIONS / CONFETTI & SCREENS
// ==========================================================================
function switchScreen(screenId) {
    const screens = ["menu-screen", "order-screen"];
    screens.forEach(s => {
        const element = document.getElementById(s);
        if (element) element.classList.remove("active-screen");
    });

    const active = document.getElementById(screenId);
    if (active) active.classList.add("active-screen");

    // Highlight nav bar active states
    document.querySelectorAll(".nav-item").forEach(nav => nav.classList.remove("active"));
    if (screenId === "menu-screen") {
        document.getElementById("nav-menu").classList.add("active");
    } else if (screenId === "order-screen") {
        document.getElementById("nav-orders").classList.add("active");
    }
}

function openOverlay(overlayId) {
    const overlay = document.getElementById(overlayId);
    if (overlay) {
        overlay.classList.add("show");
        const sheet = overlay.querySelector(".bottom-sheet") || overlay.querySelector(".drawer");
        if (sheet) sheet.classList.add("show");
    }
}

function closeOverlay(overlayId) {
    const overlay = document.getElementById(overlayId);
    if (overlay) {
        const sheet = overlay.querySelector(".bottom-sheet") || overlay.querySelector(".drawer");
        if (sheet) sheet.classList.remove("show");

        // Wait for sheet slide down animation
        setTimeout(() => {
            overlay.classList.remove("show");
        }, 250);
    }
}

function showToast(msg) {
    const toast = document.getElementById("toast-notification");
    if (!toast) return;

    document.getElementById("toast-text").textContent = msg;
    toast.classList.add("show");

    setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}

function animateCartIcon() {
    const cartNav = document.getElementById("nav-cart");
    if (cartNav) {
        cartNav.style.transform = "scale(1.25) rotate(-8deg)";
        setTimeout(() => {
            cartNav.style.transform = "scale(1) rotate(0deg)";
        }, 200);
    }
}

function triggerConfetti() {
    const container = document.getElementById("confetti-container");
    if (!container) return;
    container.innerHTML = "";

    const colors = ["#ff9800", "#ffb300", "#ffffff", "#4caf50", "#2196f3"];

    for (let i = 0; i < 60; i++) {
        const confetti = document.createElement("div");
        confetti.className = "confetti-piece";

        // Random styles
        confetti.style.left = Math.random() * 100 + "vw";
        confetti.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
        confetti.style.width = Math.random() * 8 + 6 + "px";
        confetti.style.height = Math.random() * 12 + 6 + "px";
        confetti.style.animationDelay = Math.random() * 2 + "s";
        confetti.style.animationDuration = Math.random() * 3 + 2 + "s";
        confetti.style.transform = `rotate(${Math.random() * 360}deg)`;

        container.appendChild(confetti);
    }

    // Clear confetti after 6 seconds to optimize memory
    setTimeout(() => {
        container.innerHTML = "";
    }, 6000);
}

// Interactive Credit Card inputs
function setupCreditCardInputs() {
    const numInput = document.getElementById("card-number-input");
    const ownerInput = document.getElementById("card-owner-input");

    if (numInput) {
        numInput.addEventListener("input", (e) => {
            let value = e.target.value.replace(/\D/g, '');
            // Format 1234 5678 1234 5678
            let formatted = value.substring(0, 16).replace(/(.{4})/g, '$1 ').trim();
            e.target.value = formatted;

            // Preview
            const preview = document.getElementById("card-preview-num");
            if (preview) {
                preview.textContent = formatted || "•••• •••• •••• ••••";
            }
        });
    }

    if (ownerInput) {
        ownerInput.addEventListener("input", (e) => {
            const preview = document.getElementById("card-preview-owner");
            if (preview) {
                preview.textContent = e.target.value.toUpperCase() || "NOME DO TITULAR";
            }
        });
    }
}

// ==========================================================================
// EVENT LISTENERS BINDING
// ==========================================================================
function setupEventListeners() {
    // Overlay Click handlers to close on backdrop
    document.querySelectorAll(".overlay").forEach(overlay => {
        overlay.addEventListener("click", (e) => {
            if (e.target === overlay) {
                closeOverlay(overlay.id);
            }
        });
    });

    // Form submission
    const chkForm = document.getElementById("checkout-form");
    if (chkForm) {
        chkForm.addEventListener("submit", processOrderSubmit);
    }

    // Credit card bindings
    setupCreditCardInputs();
}

// Clear cart option
function clearCart() {
    if (confirm("Deseja realmente esvaziar seu carrinho?")) {
        cart = [];
        saveCartToStorage();
        renderCartDrawer();
        showToast("Carrinho esvaziado");
    }
}

// Admin mock order history loader
function openAdminOrders() {
    alert("Área Admin: Seus pedidos concluídos são armazenados localmente e disparados via WhatsApp!");
}
