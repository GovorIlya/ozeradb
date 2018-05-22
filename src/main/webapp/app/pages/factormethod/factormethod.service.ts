import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Factormethod } from './factormethod.model';
import { createRequestOption } from '../../shared';

export type FactormethodResponseType = HttpResponse<Factormethod>;
export type FactormethodArrayResponseType = HttpResponse<Factormethod[]>;

@Injectable()
export class FactormethodService {

    private resourceUrl = SERVER_API_URL + 'api/factormethod/factormethod';

    constructor(private http: HttpClient) { }

    create(factormethod: Factormethod): Observable<FactormethodResponseType> {
        const copy = this.convert(factormethod);
        return this.http.post<Factormethod>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: FactormethodResponseType) => this.convertResponse(res));
    }

    update(factormethod: Factormethod): Observable<FactormethodResponseType> {
        const copy = this.convert(factormethod);
        return this.http.put<Factormethod>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: FactormethodResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<FactormethodResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Factormethod>(this.resourceUrl, { observe: 'response' })
            .map((res: FactormethodResponseType) => this.convertResponse(res));
    }

    private convertResponse(res: FactormethodResponseType): FactormethodResponseType {
        const body: Factormethod = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: FactormethodArrayResponseType): FactormethodArrayResponseType {
        const jsonResponse: Factormethod[] = res.body;
        const body: Factormethod[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Factormethod.
     */
    private convertItemFromServer(json: any): Factormethod {
        const copy: Factormethod = Object.assign(new Factormethod(), json);
        return copy;
    }

    /**
     * Convert a Factormethod to a JSON which can be sent to the server.
     */
    private convert(factormethod: Factormethod): Factormethod {
        const copy: Factormethod = Object.assign({}, factormethod);
        return copy;
    }
}
