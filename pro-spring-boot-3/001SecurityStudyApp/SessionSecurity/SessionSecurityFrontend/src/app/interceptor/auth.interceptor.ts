import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Clone the request and add the withCredentials option
  const modifiedReq = req.clone({
    withCredentials: true
  });

  // Forward the modified request
  return next(modifiedReq);

};
