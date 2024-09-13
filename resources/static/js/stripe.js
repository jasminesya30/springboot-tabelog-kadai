const stripe = Stripe('pk_test_51OneMWCm36JneHF26Fa7TQszJ4EDcrYeYMlDOG7QMCJyk4GWfLbRnmH7rxvSqcmaxgRm0viKCf01KO7FQQPKRjos00N6FLnJg7');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
	stripe.redirectToCheckout({
		sessionId: sessionId
	})
});