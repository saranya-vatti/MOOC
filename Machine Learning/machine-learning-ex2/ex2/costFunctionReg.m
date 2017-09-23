function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

h = sigmoid(X * theta);
% X is 118 X 28
% grad is 28 X 1
% theta is 28 X 1
% h is 118 X 1
firstterm = -y .* log(h);
secondterm = (1 - y) .* log(1 - h);
J = sum(firstterm - secondterm);
J = J/m;
theta(1, 1) = 0;
J = J + (lambda/(2*m)) * sum(theta .* theta);
tmp = h - y;
% tmp is 118 X 1
tmp1 = ((1/m) * (X' * tmp))(1, 1);
grad = ((1/m) * (X' * tmp)) + ((lambda/m)*theta);
grad(1, 1) = tmp1;

% =============================================================

end
