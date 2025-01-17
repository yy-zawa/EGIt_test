<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../header.jsp" %>
<%@include file="menu.jsp" %>

<style>
    /* 全体のラッパー */
    .wrapper {
        max-width: 1200px; /* 最大幅を設定 */
        margin: 0 auto; /* 中央寄せ */
        padding: 1rem;
    }

    /* 見出しのスタイル */
    .heading-large {
        font-size: 2rem;
        color: #0bd;
        text-align: center;
        margin-bottom: 2rem;
    }

    /* カート情報のスタイル */
    .cart-info {
        background-color: #f0f8ff;
        border: 1px solid #add8e6;
        padding: 1.5rem;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        margin-bottom: 2rem;
        text-align: center;
        font-size: 1.1rem;
        color: #333;
    }

    /* カートテーブルのスタイル */
    .cart-table {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
    }

    .cart-item {
        background-color: #fff;
        border: 1px solid #add8e6;
        border-radius: 8px;
        padding: 1rem;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
    }

    .cart-item img {
        max-width: 100%;
        height: auto;
        border-radius: 4px;
        margin-bottom: 1rem;
    }

    .cart-item h3 {
        font-size: 1.2rem;
        margin-bottom: 0.5rem;
        color: #333;
    }

    .cart-item p {
        font-size: 1rem;
        margin-bottom: 0.5rem;
        color: #555;
    }

    .cart-item .quantity {
        font-size: 1rem;
        margin-bottom: 1rem;
        color: #555;
    }

    .cart-item select {
        padding: 0.4rem;
        border: 1px solid #c9c2bc;
        border-radius: 4px;
        margin-bottom: 0.5rem;
    }

    .cart-item .update-button {
        padding: 0.5rem 1rem;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 0.9rem;
        transition: background-color 0.3s ease;
        margin-bottom: 0.5rem;
    }

    .cart-item .update-button:hover {
        background-color: #0056b3;
    }

    .cart-item a {
        padding: 0.5rem 1rem;
        background-color: #dc3545;
        color: #fff;
        text-decoration: none;
        border-radius: 4px;
        transition: background-color 0.3s ease;
    }

    .cart-item a:hover {
        background-color: #c82333;
    }

    /* 空のカートメッセージ */
    .empty-cart {
        text-align: center;
        font-size: 1.2rem;
        color: #555;
        margin-top: 2rem;
    }

    /* レスポンシブデザイン */
    @media (max-width: 768px) {
        .cart-table {
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        }

        .cart-item h3 {
            font-size: 1.1rem;
        }

        .cart-item p,
        .cart-item .quantity {
            font-size: 0.95rem;
        }

        .cart-item select,
        .cart-item .update-button {
            font-size: 0.9rem;
            padding: 0.35rem 0.7rem;
        }

        .cart-item a {
            padding: 0.4rem 0.8rem;
            font-size: 0.95rem;
        }
    }

    @media (max-width: 480px) {
        .heading-large {
            font-size: 1.5rem;
            margin-bottom: 1.5rem;
        }

        .cart-info {
            padding: 1rem;
            font-size: 1rem;
        }

        .cart-table {
            grid-template-columns: 1fr;
        }

        .cart-item h3 {
            font-size: 1rem;
        }

        .cart-item p,
        .cart-item .quantity {
            font-size: 0.9rem;
        }

        .cart-item select,
        .cart-item .update-button {
            font-size: 0.85rem;
            padding: 0.3rem 0.6rem;
        }

        .cart-item a {
            padding: 0.3rem 0.6rem;
            font-size: 0.9rem;
        }
    }
</style>

<img src="../chapter25/image/halloween_icon_11.png" width="192" height="192" alt="商品なし" style="display: block; margin: auto;">

<div class="wrapper">
    <h2 class="heading-large">カート</h2>

    <!-- 合計金額と合計個数の計算 -->
    <c:set var="totalQuantity" value="0" />
    <c:set var="totalAmount" value="0" />

    <c:forEach var="item" items="${cart}">
        <c:set var="totalQuantity" value="${totalQuantity + item.count}" />
        <c:set var="totalAmount" value="${totalAmount + (item.product.price * item.count)}" />
    </c:forEach>

    <c:choose>
        <c:when test="${not empty cart and cart.size() > 0}">
            <div class="cart-info">
                ${cart.size()}種類の商品があります。<br/>
                合計: ${totalAmount}円 (${totalQuantity}個)
            </div>
            <div class="cart-table">
                <c:forEach var="item" items="${cart}">
                    <div class="cart-item">
                        <img src="image/${item.product.name}.png" alt="商品画像${item.product.id}">
                        <h3>商品${item.product.id}: ${item.product.name}</h3>
                        <p>価格: ${item.product.price}円</p>
                        
                        <!-- 数量変更フォーム -->
                        <form action="CartUpdate.action" method="post">
                            <input type="hidden" name="id" value="${item.product.id}">
                            <label for="quantity-${item.product.id}">数量:</label>
                            <select name="quantity" id="quantity-${item.product.id}">
                                <c:forEach var="i" begin="1" end="20">
                                    <option value="${i}" <c:if test="${i == item.count}">selected</c:if>>${i}個</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="更新" class="update-button">
                        </form>
                        
                        <a href="CartRemove.action?id=${item.product.id}">カートから削除</a>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="empty-cart">
                <p>カートに商品がありません。</p>
            </div>
        </c:otherwise>
    </c:choose>
    
    <p></p>
</div>

<%@include file="../footer.jsp"%>

