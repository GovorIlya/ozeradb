import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Problemtypes } from './problemtypes.model';
import { createRequestOption } from '../../shared';

export type ProblemtypesResponseType = HttpResponse<Problemtypes>;
export type ProblemtypesArrayResponseType = HttpResponse<Problemtypes[]>;

@Injectable()
export class ProblemtypesService {

    private resourceUrl = SERVER_API_URL + 'api/problemtypes/problemtypes';

    constructor(private http: HttpClient) { }

    create(problemtypes: Problemtypes): Observable<ProblemtypesResponseType> {
        const copy = this.convert(problemtypes);
        return this.http.post<Problemtypes>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: ProblemtypesResponseType) => this.convertResponse(res));
    }

    update(problemtypes: Problemtypes): Observable<ProblemtypesResponseType> {
        const copy = this.convert(problemtypes);
        return this.http.put<Problemtypes>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: ProblemtypesResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<ProblemtypesResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Problemtypes>(this.resourceUrl, { observe: 'response' })
            .map((res: ProblemtypesResponseType) => this.convertResponse(res));
    }

    private convertResponse(res: ProblemtypesResponseType): ProblemtypesResponseType {
        const body: Problemtypes = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: ProblemtypesArrayResponseType): ProblemtypesArrayResponseType {
        const jsonResponse: Problemtypes[] = res.body;
        const body: Problemtypes[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Problemtypes.
     */
    private convertItemFromServer(json: any): Problemtypes {
        const copy: Problemtypes = Object.assign(new Problemtypes(), json);
        return copy;
    }

    /**
     * Convert a Problemtypes to a JSON which can be sent to the server.
     */
    private convert(problemtypes: Problemtypes): Problemtypes {
        const copy: Problemtypes = Object.assign({}, problemtypes);
        return copy;
    }
}
