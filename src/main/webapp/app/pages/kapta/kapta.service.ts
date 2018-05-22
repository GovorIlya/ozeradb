import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Kapta } from './kapta.model';
import { createRequestOption } from '../../shared';

export type KaptaResponseType = HttpResponse<Kapta>;
export type KaptaArrayResponseType = HttpResponse<Kapta[]>;

@Injectable()
export class KaptaService {

    private resourceUrl = SERVER_API_URL + 'api/kapta/kapta';

    constructor(private http: HttpClient) { }

    create(kapta: Kapta): Observable<KaptaResponseType> {
        const copy = this.convert(kapta);
        return this.http.post<Kapta>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: KaptaResponseType) => this.convertResponse(res));
    }

    update(kapta: Kapta): Observable<KaptaResponseType> {
        const copy = this.convert(kapta);
        return this.http.put<Kapta>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: KaptaResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<KaptaResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Kapta>(this.resourceUrl, { observe: 'response' })
            .map((res: KaptaResponseType) => this.convertResponse(res));
    }

    private convertResponse(res: KaptaResponseType): KaptaResponseType {
        const body: Kapta = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: KaptaArrayResponseType): KaptaArrayResponseType {
        const jsonResponse: Kapta[] = res.body;
        const body: Kapta[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Kapta.
     */
    private convertItemFromServer(json: any): Kapta {
        const copy: Kapta = Object.assign(new Kapta(), json);
        return copy;
    }

    /**
     * Convert a Kapta to a JSON which can be sent to the server.
     */
    private convert(kapta: Kapta): Kapta {
        const copy: Kapta = Object.assign({}, kapta);
        return copy;
    }
}
