import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

// interface UserToken {
// 	user: object,
// 	token: string,
// }

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        console.log("interceptor activated");
        // add authorization header with jwt token if available
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        
        if (currentUser && currentUser.token) {

            // let options = {
            //     headers: new HttpHeaders({
            //         'Content-Type': 'application/x-www-form-urlencoded',
            //         'jwt-auth-token': currentUser.token,
            //     }),
            // }

            request = request.clone({
                setHeaders: { 
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'jwt-auth-token': currentUser.token
                }
            });

        }else{
            console.log("no token found");
        }

        return next.handle(request);
    }
}