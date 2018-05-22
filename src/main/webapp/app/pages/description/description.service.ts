import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Description } from './description.model';
import { createRequestOption } from '../../shared';

export type DescriptionResponseType = HttpResponse<Description>;
export type DescriptionArrayResponseType = HttpResponse<Description[]>;

@Injectable()
export class DescriptionService {

    private resourceUrl = SERVER_API_URL + 'api/description/description';

    constructor(private http: HttpClient) { }

    create(description: Description): Observable<DescriptionResponseType> {
        const copy = this.convert(description);
        return this.http.post<Description>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: DescriptionResponseType) => this.convertResponse(res));
    }

    update(description: Description): Observable<DescriptionResponseType> {
        const copy = this.convert(description);
        return this.http.put<Description>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: DescriptionResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<DescriptionResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Description>(this.resourceUrl, { observe: 'response' })
            .map((res: DescriptionResponseType) => this.convertResponse(res));
    }

    private convertResponse(res: DescriptionResponseType): DescriptionResponseType {
        const body: Description = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: DescriptionArrayResponseType): DescriptionArrayResponseType {
        const jsonResponse: Description[] = res.body;
        const body: Description[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Description.
     */
    private convertItemFromServer(json: any): Description {
        const copy: Description = Object.assign(new Description(), json);
        return copy;
    }

    /**
     * Convert a Description to a JSON which can be sent to the server.
     */
    private convert(description: Description): Description {
        const copy: Description = Object.assign({}, description);
        return copy;
    }
}
