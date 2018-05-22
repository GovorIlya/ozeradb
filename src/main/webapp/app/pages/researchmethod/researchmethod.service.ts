import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Researchmethod } from './researchmethod.model';
import { createRequestOption } from '../../shared';

export type ResearchmethodResponseType = HttpResponse<Researchmethod>;
export type ResearchmethodArrayResponseType = HttpResponse<Researchmethod[]>;

@Injectable()
export class ResearchmethodService {

    private resourceUrl = SERVER_API_URL + 'api/researchmethod/researchmethod';

    constructor(private http: HttpClient) { }

    create(researchmethod: Researchmethod): Observable<ResearchmethodResponseType> {
        const copy = this.convert(researchmethod);
        return this.http.post<Researchmethod>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: ResearchmethodResponseType) => this.convertResponse(res));
    }

    update(researchmethod: Researchmethod): Observable<ResearchmethodResponseType> {
        const copy = this.convert(researchmethod);
        return this.http.put<Researchmethod>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: ResearchmethodResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<ResearchmethodResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Researchmethod>(this.resourceUrl, { observe: 'response' })
            .map((res: ResearchmethodResponseType) => this.convertResponse(res));
    }

    private convertResponse(res: ResearchmethodResponseType): ResearchmethodResponseType {
        const body: Researchmethod = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: ResearchmethodArrayResponseType): ResearchmethodArrayResponseType {
        const jsonResponse: Researchmethod[] = res.body;
        const body: Researchmethod[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Researchmethod.
     */
    private convertItemFromServer(json: any): Researchmethod {
        const copy: Researchmethod = Object.assign(new Researchmethod(), json);
        return copy;
    }

    /**
     * Convert a Researchmethod to a JSON which can be sent to the server.
     */
    private convert(researchmethod: Researchmethod): Researchmethod {
        const copy: Researchmethod = Object.assign({}, researchmethod);
        return copy;
    }
}
