import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Teachstate } from './teachstate.model';
import { createRequestOption } from '../../shared';

export type TeachstateResponseType = HttpResponse<Teachstate>;
export type TeachstateArrayResponseType = HttpResponse<Teachstate[]>;

@Injectable()
export class TeachstateService {

    private resourceUrl = SERVER_API_URL + 'api/teachstate/teachstate';

    constructor(private http: HttpClient) { }

    create(teachstate: Teachstate): Observable<TeachstateResponseType> {
        const copy = this.convert(teachstate);
        return this.http.post<Teachstate>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: TeachstateResponseType) => this.convertResponse(res));
    }

    update(teachstate: Teachstate): Observable<TeachstateResponseType> {
        const copy = this.convert(teachstate);
        return this.http.put<Teachstate>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: TeachstateResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<TeachstateResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Teachstate>(this.resourceUrl, { observe: 'response' })
            .map((res: TeachstateResponseType) => this.convertResponse(res));
    }

    private convertResponse(res: TeachstateResponseType): TeachstateResponseType {
        const body: Teachstate = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: TeachstateArrayResponseType): TeachstateArrayResponseType {
        const jsonResponse: Teachstate[] = res.body;
        const body: Teachstate[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Teachstate.
     */
    private convertItemFromServer(json: any): Teachstate {
        const copy: Teachstate = Object.assign(new Teachstate(), json);
        return copy;
    }

    /**
     * Convert a Teachstate to a JSON which can be sent to the server.
     */
    private convert(teachstate: Teachstate): Teachstate {
        const copy: Teachstate = Object.assign({}, teachstate);
        return copy;
    }
}
