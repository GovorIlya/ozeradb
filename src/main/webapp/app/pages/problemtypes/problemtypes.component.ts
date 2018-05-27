import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
 import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
 import { Observable } from 'rxjs/Rx';

import { Problemtypes } from './problemtypes.model';
import { ProblemtypesService } from './problemtypes.service';
import { Principal } from '../../shared';

import {Unit} from '../../entities/unit';
import {UnitService} from '../../entities/unit';
import {TypesProblems} from '../../entities/types-problems';
import {TypesProblemsService} from '../../entities/types-problems';
import {Regions} from './regions.model';


@Component({
    selector: 'jhi-problemtypes',
    templateUrl: './problemtypes.component.html'
})
export class ProblemtypesComponent implements OnInit, OnDestroy {
    regions: Regions[] =[
        {Id:1, Name: 'Минская'},
        {Id:2, Name: 'Гомельская'},
        {Id:3, Name: 'Могилевская'},
        {Id:4, Name: 'Витебская'},
        {Id:5, Name: 'Гродненская'},
        {Id:6, Name: 'Брестская'}
    ];
    btnState:boolean;
    regionName: string;

    unName: string;
    uProblemId: number;
    unId: number;

    units: Unit[];
    typesProblems: TypesProblems[];

    problemtypes: Problemtypes = new Problemtypes();

    currentAccount: any;
    eventSubscriber: Subscription;
    isSaving: Boolean;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private unitService: UnitService,
        private typesProblemsService: TypesProblemsService,
        private problemtypesService: ProblemtypesService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.unitService.query().subscribe(
            (res: HttpResponse<Unit[]>) => {
                this.units = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.typesProblemsService.query().subscribe(
            (res: HttpResponse<TypesProblems[]>) => {
                this.typesProblems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.btnState=true;
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

        this.registerChangeInProblemtypes();
    }

    ngOnDestroy() {

        this.eventManager.destroy(this.eventSubscriber);
    }
    registerChangeInProblemtypes() {
        this.eventSubscriber = this.eventManager.subscribe('problemtypesListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }


    onClickMe(uId:any) {
      //  this.unId=this.units[uId].id;
       // this.unName=this.units[uId].unitName;
       // this.uProblemId=this.units[uId].typesProblems.id;

        for (let un of this.units) {
                if (un.id == uId) {
                    this.unName = un.unitName;
                    this.unId=un.id;
                    this.uProblemId=un.typesProblems.id;
                }
            }
        }
        // this.uFactorId=this.units[uId].researchMethod.id;



    onClickRegion(regname: string){
        this.btnState=false;
        this.regionName=regname;


        this.unName = "";


    }
}
