document.addEventListener("DOMContentLoaded", function () {
    let creditCardCheckBox = document.getElementById("creditCardCheckBox");
    let paypalCheckBox = document.getElementById("paypalCheckBox");
    let container = document.getElementById("paymentMethod");

    creditCardCheckBox.addEventListener("change", function () {
        if (this.checked) {
            container.innerHTML =
                `<div class="row gy-3">
                                <div class="col-md-6">
                                    <label for="cc-name" class="form-label">Name on card</label>
                                    <input type="text" class="form-control" id="cc-name" required="">
                                    <small class="text-muted">Full name as displayed on card</small>
                                    <div class="invalid-feedback">
                                        Name on card is required
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <label for="cc-number" class="form-label">Credit card number</label>
                                    <input type="text" class="form-control" id="cc-number" required="">
                                    <div class="invalid-feedback">
                                        Credit card number is required
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <label for="cc-expiration" class="form-label">Expiration date</label>
                                    <input type="text" class="form-control" id="cc-expiration" required="">
                                    <div class="invalid-feedback">
                                        Expiration date required
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <label for="cc-cvv" class="form-label">CVV</label>
                                    <input type="text" class="form-control" id="cc-cvv" required="">
                                    <div class="invalid-feedback">
                                        Security code required
                                    </div>
                                </div>
                            </div>`
        }
    })
    paypalCheckBox.addEventListener("change", function () {
        if (this.checked) {
            container.innerHTML =
                `                <div class="row gy-4">
                                <div class="col-md-6">
                                    <label for="cc-name" class="form-label">E-mail</label>
                                    <input type="email" class="form-control" id="paypalEmail" placeholder="name@domain.com" required>
                                    <div class="invalid-feedback">
                                        E-mail is required
                                    </div>
                                <div class="row gy-4">
                                    <div class="col-md-6">
                                        <label for="paypalPassword1" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="paypalPassword1" placeholder="Password" required>
                                         <div class="invalid-feedback">
                                            Password is required
                                        </div>
                                      </div>
                                                                             
                                        <div class="col-md-6">
                                        <label for="paypalPassword2" class="form-label">Rep. password</label>
                                        <input type="password" class="form-control" id="paypalPassword2" placeholder="Repeat password" required>
                                        <div class="invalid-feedback">
                                            Repeated password is required
                                        </div>
                                        </div>
                                        
                                </div>
                            </div>`
        }
    })
})

let closeCartBtt = document.getElementById("cartClose")
let paymentBtt = document.getElementById("paymentButton")
paymentBtt.addEventListener("click", function (){
    closeCartBtt.click();
})
