# 🍔 Marmitaria do Dia - Aplicativo de Pedidos Mobile-First

Este é um aplicativo moderno, elegante e otimizado para celulares (**Mobile-First**) voltado para o comércio de refeições diárias (marmitex). O aplicativo foi projetado com base nas imagens e no cardápio real do estabelecimento, apresentando um design escuro rústico e texturas premium simulando madeira escura e acabamentos dourados/alaranjados, ideal para gastronomia de alto padrão.

O aplicativo opera como uma **Single-Page Application (SPA)** de alto desempenho construída inteiramente com **HTML5 Semântico**, **CSS3 Customizado** (sem frameworks pesados ou dependências externas) e **JavaScript Vanila**.

---

## 🎨 Design & Estética Premium

*   **Identidade Visual:** Fundo escuro inspirado em madeira de demolição rústica obtido com gradientes CSS dinâmicos e overlays de ruído fino, títulos brancos condensados e elegantes em destaque, cursivas manuscritas na cor Amarelo Mel (`#ffb300`) e tags de ações em Laranja Vibrante (`#ff9800`).
*   **Fontes do Sistema:**
    *   `Oswald`: Para títulos principais de impacto e numerações de pratos.
    *   `Dancing Script`: Para detalhes decorativos elegantes manuscritos (como o "do Dia").
    *   `Inter`: Para textos de corpo confortáveis, garantindo excelente leitura.
*   **Efeitos Modernos:**
    *   **Glassmorphic Drawers:** Painéis laterais deslizantes (Carrinho e Checkout) e modais inferiores (*bottom sheets*) com efeito de vidro fosco reflexivo (`backdrop-filter: blur(16px)`).
    *   **3D Hover Cards:** Cartões de comida que reagem de forma tridimensional ao toque ou cursor do mouse.
    *   **Micro-animações:** Botões com efeitos pulsantes lentos, transições fluidas de telas, toast-notifications dinâmicas e **chuva de confetes coloridos** após fechar uma compra.

---

## ⚙️ Funcionalidades Técnicas

1.  **Detecção Automática do Dia:**
    *   O aplicativo lê automaticamente o dia atual da semana no dispositivo do cliente.
    *   Caso seja domingo (estabelecimento fechado), o sistema exibe por padrão o cardápio de Segunda-feira com um banner vermelho no topo: *"ESTAMOS FECHADOS HOJE (DOMINGO) - Consulte o menu abaixo e agende seu pedido!"*.
2.  **Seletor Inteligente de Dias:**
    *   Um carrossel horizontal de rolagem suave no topo permite que o cliente navegue facilmente entre os cardápios de **Segunda a Sábado** e agende pedidos futuros.
3.  **Fidelidade ao Cardápio e Correções:**
    *   O banco de dados do JavaScript contém todos os pratos das imagens fornecidas na pasta `Cardápio/` estruturados e com as correções gramaticais necessárias (*"Bife a Cavalo"* ao invés de "Acavako", *"Arroz à Grega"* ao invés de "Agrega", etc.).
    *   **Preços Oficiais:** Todas as marmitas normais custam **R$ 27,00** e as opções de Feijoada Light (Quarta e Sábado - Opção 1) custam **R$ 40,00**.
    *   **Bebidas:** Refrigerantes Lata 350ml por **R$ 6,00** e Mini Refrigerantes 200ml por **R$ 3,00**.
4.  **Customização do Marmitex:**
    *   Ao clicar em um prato, abre-se uma gaveta inferior para o cliente escolher opcionais extras (Ovo frito, Batata Palha, Farofa) com reajuste automático no subtotal, além de uma caixa de observações para retirar ingredientes (ex: *"Sem cebola"*, *"Arroz separado"*).
5.  **Carrinho e Checkout:**
    *   Gerenciamento completo de carrinho com ajuste de quantidades (+ / -) e botão para limpar carrinho.
    *   Opção entre **Entrega** (com formulário completo de endereço: Rua, Número, Bairro, CEP e Referência) e **Retirada no Balcão** (que oculta o formulário e exibe o endereço do restaurante com horários de retirada).
6.  **Simulação de Pagamentos Integrada:**
    *   **PIX:** Exibe um QR code customizado com logotipo e botão interativo para copiar a chave Pix (número do celular).
    *   **Cartão de Crédito Online:** Apresenta uma simulação 3D dinâmica do cartão de crédito que atualiza o número e o nome do titular conforme o usuário digita no formulário.
    *   **Pagar na Entrega:** Escolha entre dinheiro físico (com calculadora inteligente para calcular o troco) e maquininha no portão.
7.  **Rastreador de Pedidos:**
    *   Após a confirmação do pagamento, confetes são disparados e a tela muda para o Rastreador.
    *   A linha do tempo atualiza o status dinamicamente em 4 etapas (*Recebido, Preparando, Saiu para Entrega, Entregue*) registrando os horários de alteração em tempo real.
8.  **Disparador e Redirecionamento Oficial para WhatsApp:**
    *   O botão principal na tela de sucesso gera uma mensagem em formato profissional em português brasileiro com a formatação em Markdown do WhatsApp.
    *   Ele monta a lista com todos os itens, adicionais, subtotal, taxa (grátis), endereço completo de entrega, observações e forma de pagamento, direcionando o cliente com apenas 1 clique para o WhatsApp oficial: **`5511970599173`**.

---

## 📁 Estrutura de Arquivos

O projeto está organizado da seguinte forma no seu diretório:

```text
Marmitex/
│
├── Cardápio/                  # Imagens originais fornecidas organizadas por dia
│   ├── 02 - Segunda/
│   ├── 03 - Terça/
│   ├── 04 - Quarta/
│   ├── 05 - Quinta/
│   ├── 06 - Sexta/
│   └── 07 - Sábado/
│
├── index.html                 # Estrutura principal da SPA
├── style.css                  # Folha de estilos premium, responsiva e móvel
├── app.js                     # Banco de dados de cardápio e controle lógico de estado
└── README.md                  # Documentação técnica e guia (Este arquivo)
```

---

## 🚀 Como Executar o Projeto Localmente

Como o projeto é desenvolvido com tecnologias puras da Web (HTML/CSS/JS), você não precisa de nenhuma etapa complexa de compilação ou instalação de servidores robustos.

### Opção 1: Abrir diretamente no navegador (Fácil)
Basta dar um duplo clique no arquivo `index.html` ou arrastá-lo para dentro de qualquer navegador moderno (Chrome, Safari, Firefox, Edge).

### Opção 2: Executar um servidor local leve (Recomendado para simulação perfeita)
Se você deseja executar o projeto em uma porta local simulando um servidor real de produção (para testar recursos offline de armazenamento e evitar regras rígidas de segurança local), execute no terminal dentro da pasta raiz do projeto:

**Usando Python (instalado por padrão no Windows/Mac/Linux):**
```bash
python -m http.server 8080
```
Depois, acesse no seu navegador: **`http://localhost:8080`**

**Usando Node.js / NPX:**
```bash
npx http-server ./ -p 8080
```
Depois, acesse no seu navegador: **`http://localhost:8080`**

---

## 🤝 Créditos

Desenvolvido para proporcionar a melhor experiência de delivery rápida e satisfatória aos clientes da Marmitaria. Bom apetite! 😋
